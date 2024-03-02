package com.bbshop.service;

import cn.hutool.core.util.ObjectUtil;
import com.bbshop.common.Constants;
import com.bbshop.common.constant.CodeEnum;
import com.bbshop.common.constant.RoleEnum;
import com.bbshop.common.constant.StatusEnum;
import com.bbshop.entity.Account;
import com.bbshop.entity.Shop;
import com.bbshop.exception.CustomException;
import com.bbshop.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;

import java.util.List;

public interface ShopService {
    public void add(Shop shop) ;

    /**
     * 删除
     */
    public void deleteById(Integer id);

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) ;

    /**
     * 修改
     */
    public void updateById(Shop shop,String code,Boolean emailState);

    /**
     * 根据ID查询
     */
    public Shop selectById(Integer id);

    /**
     * 查询所有
     */
    public List<Shop> selectAll(Shop shop);

    /**
     * 分页查询
     */
    public PageInfo<Shop> selectPage(Shop shop, Integer pageNum, Integer pageSize) ;




}
