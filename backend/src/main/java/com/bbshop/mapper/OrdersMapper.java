package com.bbshop.mapper;

import com.bbshop.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作orders相关数据接口
*/
@Mapper
public interface OrdersMapper {

    /**
      * 新增
    */
    int insert(Orders orders);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Orders orders);

    /**
      * 根据ID查询
    */
    Orders selectById(Integer id);

    /**
      * 查询所有、条件查询：订单号/商品名称
    */
    /**
     * 查询所有、条件查询：订单号/商品名称
     */
//    List<Orders> selectBase(Orders orders);
    List<Orders> selectAll(Orders orders);




    @Select("select * from orders where status = '已完成' or status = '已评价'")
    List<Orders> selectAllOKOrders();

}