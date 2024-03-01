package com.bbshop.service;

import com.bbshop.entity.Orders;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrdersService {

    /**
     * 查询所有
     */
    public List<Orders> selectAll(Orders orders);

    /**
     * 分页查询
     */
    public PageInfo<Orders> selectPage(Orders orders, Integer pageNum, Integer pageSize) ;

    /**
     * 删除
     */
    public void deleteById(Integer id) ;
    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) ;
}
