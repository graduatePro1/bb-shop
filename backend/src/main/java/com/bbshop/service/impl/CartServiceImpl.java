package com.bbshop.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.bbshop.common.constant.RoleEnum;
import com.bbshop.entity.Account;
import com.bbshop.entity.Cart;
import com.bbshop.mapper.CartMapper;
import com.bbshop.service.CartService;
import com.bbshop.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private CartMapper cartMapper;

    /**
     * 新增
     */
    public void add(Cart cart) {
        // 判断该用户对该商品有没有加入过购物车，如果加入过，那么只要更新一下该条记录的num（+1）
        Cart dbCart = cartMapper.selectByUserIdAndGoodsId(cart.getUserId(), cart.getGoodsId());
        if (ObjectUtil.isNotEmpty(dbCart)) {
            dbCart.setNum(dbCart.getNum() + 1);
            cartMapper.updateById(dbCart);
        } else {
            cartMapper.insert(cart);
        }
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        cartMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            cartMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Cart cart) {
        cartMapper.updateById(cart);
    }

    /**
     * 根据ID查询
     */
    public Cart selectById(Integer id) {
        return cartMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Cart> selectAll(Cart cart) {
        return cartMapper.selectAll(cart);
    }

    /**
     * 分页查询
     */
    public PageInfo<Cart> selectPage(Cart cart, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            cart.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Cart> list = cartMapper.selectAll(cart);
        return PageInfo.of(list);
    }
}

