package com.bbshop.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.bbshop.common.Constants;
import com.bbshop.common.constant.CodeEnum;
import com.bbshop.common.constant.RoleEnum;
import com.bbshop.entity.Account;
import com.bbshop.entity.User;
import com.bbshop.exception.CustomException;
import com.bbshop.mapper.UserMapper;
import com.bbshop.service.UserService;
import com.bbshop.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    StringRedisTemplate template;

    @Value("${spring.mail.username}")
    String from;
    @Resource
    MailSender mailSender;//邮件发送的接口
    /**
     * 新增
     */
    public void add(User user) {
        User dbUser = userMapper.selectByUsernameOrEmail(user.getUsername());
        if (ObjectUtil.isNotNull(dbUser)) {
            throw new CustomException(CodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(user.getPassword())) {
            user.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(user.getName())) {
            user.setName(user.getUsername());
        }
        user.setRole(RoleEnum.USER.name());
        userMapper.insert(user);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            userMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(User user,String code,Boolean emailState) {
        //用户修改了邮箱，需要检查邮箱是否可用
        if(emailState){
            //对比验证码是否正确
            String key = "email:" + user.getEmail() + ":false";
            if (Boolean.TRUE.equals(template.hasKey(key))) {
                String s = template.opsForValue().get(key); //获取剩余时间
                if (s == null)
                    throw new CustomException(CodeEnum.CODE_EXIST_ERROR); //验证码恰好失效
                //已发送了验证码，判断是否正确
                if (!s.equals(code)) { //不正确
                    throw new CustomException(CodeEnum.PARAM_CODE_ERROR);
                }
                template.delete(key); //删除key
                userMapper.updateById(user); //通过验证后才修改
            }else {
                throw new CustomException(CodeEnum.CODE_EXIST_ERROR);
            }
        }else { //用户没有修改邮箱，直接修改信息
            userMapper.updateById(user);
        }


    }

    @Override
    public int updateByEmail(User user) {
        return userMapper.updateByEmail(user);
    }

    /**
     * 根据ID查询
     */
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<User> selectAll(User user) {
        return userMapper.selectAll(user);
    }

    /**
     * 分页查询
     */
    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll(user);
        return PageInfo.of(list);
    }




}
