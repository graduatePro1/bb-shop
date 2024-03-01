package com.bbshop.mapper;

import com.bbshop.entity.Shop;
import com.bbshop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作business相关数据接口
*/
@Mapper
public interface ShopMapper {

    /**
      * 新增
    */
    int insert(Shop shop);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Shop shop);

    /**
      * 根据ID查询
    */
    Shop selectById(Integer id);

    /**
      * 查询所有
    */
    List<Shop> selectAll(Shop shop);

    @Select("select * from shop where username = #{text} or email=#{text}")
    Shop selectByUsernameOrEmail(String text);
}