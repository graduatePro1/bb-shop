package com.bbshop.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.bbshop.common.constant.CodeEnum;
import com.bbshop.common.constant.RoleEnum;
import com.bbshop.entity.Account;
import com.bbshop.entity.Shop;
import com.bbshop.entity.User;
import com.bbshop.exception.CustomException;
import com.bbshop.mapper.ShopMapper;
import com.bbshop.mapper.UserMapper;
import com.bbshop.service.BaseService;
import com.bbshop.service.ShopService;
import com.bbshop.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class BaseServiceImpl implements BaseService {
    @Resource
    StringRedisTemplate template;

    @Value("${spring.mail.username}")
    String from;
    @Resource
    MailSender mailSender;//邮件发送的接口

    @Resource
    private UserMapper userMapper;

    @Resource
    private ShopMapper shopMapper;

    //发送验证码
    public String sendEmailCode(String email, boolean hasUser,String role) {
        /**
         * 1、首先生成验证码
         * 2、把用户输入的邮箱和验证码组成键值对的形式存放到redis中（设置过期时间，重新发送验证码时需要低于几分钟再重新发送，重复此流程）
         * 3、发送验证码到指定的邮箱
         * 4、如果发送失败，再把刚存入redis的键值对删除
         * 5、发送正确，用户再从redis里取出键值对，然后判断用户输入的和redis的验证码是否一致
         */
        //        执行前再判断邮箱地址并且判断是否为同一个sessionid防止恶意注册

        String key = "email:" + email + ":" + hasUser;//设置键值对

        //判断是否存在键值对，并且判断剩余时间是否满足重新发送
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            Long expire = Optional.ofNullable(template.getExpire(key, TimeUnit.SECONDS)).orElse(0L);

            if (expire > 120)
                return "请求过于频繁，请稍后重试";
        }
        //不同身份判断邮箱是否已被注册
        if(RoleEnum.USER.name().equals(role)){ //用户身份
            if (!hasUser && userMapper.selectByUsernameOrEmail(email) != null )
                return "此邮箱已被其他用户注册";

            if (hasUser && userMapper.selectByUsernameOrEmail(email) == null)
                return "该邮箱并未注册";
        }else if(RoleEnum.SHOP.name().equals(role)){ //上架
            if (!hasUser && shopMapper.selectByUsernameOrEmail(email) != null )
                return "此邮箱已被其他用户注册";

            if (hasUser && shopMapper.selectByUsernameOrEmail(email) == null)
                return "该邮箱并未注册";
        }
//        else if(RoleEnum.ADMIN.name().equals(role)){ //上架
//            if (!hasUser && adminMapper.selectByUsernameOrEmail(email) != null )
//                return "此邮箱已被其他用户注册";
//
//            if (hasUser && adminMapper.selectByUsernameOrEmail(email) == null)
//                return "该邮箱并未注册";
//        }


        Random random = new Random();
        int code = random.nextInt(899999) + 100000;
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(email);
        mailMessage.setSubject("您的验证邮件");
        mailMessage.setText("验证码是：" + code + "。您正在登录，若非本人操作，请勿泄露。");

        try {
            mailSender.send(mailMessage);
            template.opsForValue().set(key, String.valueOf(code), 3, TimeUnit.MINUTES);//存入redis中并设置存活时间
            return "验证码发送成功";

        } catch (MailException e) {
            e.printStackTrace();
        }
        return "验证码发送失败，请联系管理员";
    }

//    检查验证码
    @Override
    public String checkCode(String email, String code,String role) {
        Account account=new Account();
        if(RoleEnum.USER.name().equals(role)){
            account= userMapper.selectByUsernameOrEmail(email);
        }else if(RoleEnum.SHOP.name().equals(role)){
            account= shopMapper.selectByUsernameOrEmail(email);
        }
//        else if(RoleEnum.ADMIN.name().equals(role)){
//            Account dbUser= userMapper.selectByUsernameOrEmail(email);
//        }


        if (ObjectUtil.isNull(account)) {
            throw new CustomException(CodeEnum.USER_NOT_EXIST_ERROR);  //无此邮箱的用户
        }
        //获取验证码
        String key = "email:"  + email + ":true";
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            String s = template.opsForValue().get(key);
            template.delete(key); //删除key
            if (s == null)
                throw new CustomException(CodeEnum.CODE_EXIST_ERROR); //验证码恰好失效
            //已发送了验证码，判断是否正确
            if (!s.equals(code)) { //不正确
                throw new CustomException(CodeEnum.PARAM_CODE_ERROR);
            }
        }else {
            throw new CustomException(CodeEnum.CODE_EXIST_ERROR);
        }
        return "验证成功";
    }

    /**
     * 登录
     * @param account
     * @return
     */
    public Account login(Account account) {
        Account dbUser=new Account();
        if(ObjectUtil.isEmpty(account.getEmail())){ //密码方式登录

            if(RoleEnum.USER.name().equals(account.getRole())){
                dbUser = userMapper.selectByUsernameOrEmail(account.getUsername());
            } else if (RoleEnum.SHOP.name().equals(account.getRole())) {
                dbUser = shopMapper.selectByUsernameOrEmail(account.getUsername());
            }

            if (ObjectUtil.isNull(dbUser)) {
                throw new CustomException(CodeEnum.USER_NOT_EXIST_ERROR);
            }
            if (!account.getPassword().equals(dbUser.getPassword())) {
                throw new CustomException(CodeEnum.USER_ACCOUNT_ERROR);
            }
        }else { //邮箱方式登录
            if(RoleEnum.USER.name().equals(account.getRole())){
                dbUser = userMapper.selectByUsernameOrEmail(account.getEmail());
            } else if (RoleEnum.SHOP.name().equals(account.getRole())) {
                dbUser = shopMapper.selectByUsernameOrEmail(account.getEmail());
            }

            if (ObjectUtil.isNull(dbUser)) {
                throw new CustomException(CodeEnum.USER_NOT_EXIST_ERROR);  //无此邮箱的用户
            }
            //获取验证码
            String key = "email:"  + account.getEmail() + ":true";
            if (Boolean.TRUE.equals(template.hasKey(key))) {
                String s = template.opsForValue().get(key);
                if (s == null)
                    throw new CustomException(CodeEnum.CODE_EXIST_ERROR); //验证码恰好失效
                //已发送了验证码，判断是否正确
                if (!s.equals(account.getCode())) { //不正确
                    throw new CustomException(CodeEnum.PARAM_CODE_ERROR);
                }
                template.delete(key); //删除key
            }else {
                throw new CustomException(CodeEnum.CODE_EXIST_ERROR);
            }
        }
        // 验证都成功了，生成token
        String tokenData = dbUser.getId() + "-" + account.getRole();
        System.out.println(tokenData);
        String token = TokenUtils.createToken(tokenData, dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    /**
     * 修改密码
     */
    public void updatePassword(Account account){

        if(RoleEnum.USER.name().equals(account.getRole())){
           User dbUser =userMapper.selectByUsernameOrEmail(account.getUsername());
            if (ObjectUtil.isNull(dbUser)) {
                throw new CustomException(CodeEnum.USER_NOT_EXIST_ERROR);
            }
            if (!account.getPassword().equals(dbUser.getPassword())) {
                throw new CustomException(CodeEnum.PARAM_PASSWORD_ERROR);
            }
            dbUser.setPassword(account.getNewPassword());
            userMapper.updateById(dbUser);
        }
        else if (RoleEnum.SHOP.name().equals(account.getRole())) {
          Shop dbUser = shopMapper.selectByUsernameOrEmail(account.getUsername());
            if (ObjectUtil.isNull(dbUser)) {
                throw new CustomException(CodeEnum.USER_NOT_EXIST_ERROR);
            }
            if (!account.getPassword().equals(dbUser.getPassword())) {
                throw new CustomException(CodeEnum.PARAM_PASSWORD_ERROR);
            }
            dbUser.setPassword(account.getNewPassword());
            shopMapper.updateById(dbUser);
        }
    }

    /**
     * 注册
     * @param account
     */
    public void register(Account account) {
        if(RoleEnum.USER.name().equals(account.getRole())) {
            if(ObjectUtil.isNotNull(userMapper.selectByUsernameOrEmail(account.getUsername()))){
                throw new CustomException(CodeEnum.USER_EXIST_ERROR);
            }
            //判断用户名/邮箱是否唯一
            if(ObjectUtil.isNotNull(userMapper.selectByUsernameOrEmail(account.getEmail()))){
                throw new CustomException(CodeEnum.EMAIL_EXIST_ERROR);
            }
        }
        else if(RoleEnum.SHOP.name().equals(account.getRole())) {
            if(ObjectUtil.isNotNull(shopMapper.selectByUsernameOrEmail(account.getUsername()))){
                throw new CustomException(CodeEnum.USER_EXIST_ERROR);
            }
            //判断用户名/邮箱是否唯一
            if(ObjectUtil.isNotNull(shopMapper.selectByUsernameOrEmail(account.getEmail()))){
                throw new CustomException(CodeEnum.EMAIL_EXIST_ERROR);
            }
        }
        //判断是否获取验证码了
        //获取验证码
        String key = "email:"  + account.getEmail() + ":false";
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            String s = template.opsForValue().get(key);
            if (s == null)
                throw new CustomException(CodeEnum.CODE_EXIST_ERROR); //验证码恰好失效
            //已发送了验证码，判断是否正确
            if (!s.equals(account.getCode())) { //不正确
                throw new CustomException(CodeEnum.PARAM_CODE_ERROR);
            }
            template.delete(key); //删除key
        }else {
            throw new CustomException(CodeEnum.CODE_EXIST_ERROR);
        }

        if(RoleEnum.USER.name().equals(account.getRole())) {
            User user = new User();
            BeanUtils.copyProperties(account, user);
            user.setName(user.getUsername()); //初始化昵称为用户名
            userMapper.insert(user);
        }
        else if(RoleEnum.SHOP.name().equals(account.getRole())) {
            Shop shop = new Shop();
            BeanUtils.copyProperties(account, shop);
            shop.setName(shop.getUsername()); //初始化昵称为用户名
            shopMapper.insert(shop);
        }

    }

}
