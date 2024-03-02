package com.bbshop.service;

import cn.hutool.core.util.ObjectUtil;
import com.bbshop.common.constant.RoleEnum;
import com.bbshop.entity.Account;
import com.bbshop.entity.Cart;
import com.bbshop.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CartService {

    /**
     * 新增
     */
    public void add(Cart cart);
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
    public void updateById(Cart cart);

    /**
     * 根据ID查询
     */
    public Cart selectById(Integer id);
    /**
     * 查询所有
     */
    public List<Cart> selectAll(Cart cart) ;
    /**
     * 分页查询
     */
    public PageInfo<Cart> selectPage(Cart cart, Integer pageNum, Integer pageSize);
}
