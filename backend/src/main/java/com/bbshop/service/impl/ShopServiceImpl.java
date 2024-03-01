package com.bbshop.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.bbshop.common.Constants;
import com.bbshop.common.constant.CodeEnum;
import com.bbshop.common.constant.RoleEnum;
import com.bbshop.common.constant.StatusEnum;
import com.bbshop.entity.Account;
import com.bbshop.entity.Shop;
import com.bbshop.exception.CustomException;
import com.bbshop.mapper.ShopMapper;
import com.bbshop.service.ShopService;
import com.bbshop.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Resource
    private ShopMapper shopMapper;

    @Resource
    StringRedisTemplate template;

    @Value("${spring.mail.username}")
    String from;
    @Resource
    MailSender mailSender;//邮件发送的接口
    /**
     * 新增
     */
    public void add(Shop shop) {
        Shop dbBusiness = shopMapper.selectByUsernameOrEmail(shop.getUsername());
        if (ObjectUtil.isNotNull(dbBusiness)) {
            throw new CustomException(CodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(shop.getPassword())) {
            shop.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(shop.getName())) {
            shop.setName(shop.getUsername());
        }
        if (ObjectUtil.isEmpty(shop.getStatus())) {
            shop.setStatus(StatusEnum.CHECKING.status);
        }
        shop.setRole(RoleEnum.SHOP.name());
        shopMapper.insert(shop);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        shopMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            shopMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Shop shop,String code,Boolean emailState) {
        //用户修改了邮箱，需要检查邮箱是否可用
        if(emailState){
            //对比验证码是否正确
            String key = "email:" + shop.getEmail() + ":false";
            if (Boolean.TRUE.equals(template.hasKey(key))) {
                String s = template.opsForValue().get(key); //获取剩余时间
                if (s == null)
                    throw new CustomException(CodeEnum.CODE_EXIST_ERROR); //验证码恰好失效
                //已发送了验证码，判断是否正确
                if (!s.equals(code)) { //不正确
                    throw new CustomException(CodeEnum.PARAM_CODE_ERROR);
                }
                template.delete(key); //删除key
                shopMapper.updateById(shop); //通过验证后才修改
            }else {
                throw new CustomException(CodeEnum.CODE_EXIST_ERROR);
            }
        }else { //用户没有修改邮箱，直接修改信息
            shopMapper.updateById(shop);
        }
    }

    /**
     * 根据ID查询
     */
    public Shop selectById(Integer id) {
        return shopMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Shop> selectAll(Shop shop) {
        return shopMapper.selectAll(shop);
    }

    /**
     * 分页查询
     */
    public PageInfo<Shop> selectPage(Shop business, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shop> list = shopMapper.selectAll(business);
        return PageInfo.of(list);
    }




}
