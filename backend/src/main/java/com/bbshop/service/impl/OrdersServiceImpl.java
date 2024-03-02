package com.bbshop.service.impl;

import cn.hutool.core.date.DateUtil;
import com.bbshop.common.constant.RoleEnum;
import com.bbshop.entity.Account;
import com.bbshop.entity.Cart;
import com.bbshop.entity.Orders;
import com.bbshop.mapper.CartMapper;
import com.bbshop.mapper.OrdersMapper;
import com.bbshop.service.OrdersService;
import com.bbshop.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private CartMapper cartMapper;
    /**
     * 查询所有
     */
    public List<Orders> selectAll(Orders orders) {
        return ordersMapper.selectAll(orders);
    }

    /**
     * 分页查询
     */
    public PageInfo<Orders> selectPage(Orders orders, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            orders.setUserId(currentUser.getId());
        }
        if (RoleEnum.SHOP.name().equals(currentUser.getRole())) {
            orders.setShopId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list = ordersMapper.selectAll(orders);
        return PageInfo.of(list);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        ordersMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            ordersMapper.deleteById(id);
        }
    }

    /**
     * 新增
     */
    public void add(Orders orders) {
        Random random = new Random();

        for (Cart cart : orders.getCartData()) {
            int random_code=random.nextInt(899999)+100000;
            String orderId=DateUtil.format(new Date(), "yyyyMMddHHmmss")+ String.valueOf(random_code) ;
            orders.setOrderId(orderId);

            Orders dbOrders = new Orders();
            BeanUtils.copyProperties(orders, dbOrders);
            dbOrders.setGoodsId(cart.getGoodsId());
            dbOrders.setShopId(cart.getShopId());
            dbOrders.setNum(cart.getNum());
            dbOrders.setPrice(cart.getNum() * cart.getGoodsPrice());
            ordersMapper.insert(dbOrders);
            // 把购物车里对应的商品删掉
            cartMapper.deleteById(cart.getId());
        }
    }
}
