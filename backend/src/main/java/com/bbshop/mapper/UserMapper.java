package com.bbshop.mapper;

import com.bbshop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作user相关数据接口
*/
@Mapper
public interface UserMapper {

    /**
      * 新增
    */
    int insert(User user);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(User user);

    int updateByEmail(User user);//根据邮箱修改密码

    /**
      * 根据ID查询
    */
    User selectById(Integer id);

    /**
      * 查询所有
    */
    List<User> selectAll(User user);

    @Select("select * from user where username = #{text} or email=#{text}")
    User selectByUsernameOrEmail(String text);
}