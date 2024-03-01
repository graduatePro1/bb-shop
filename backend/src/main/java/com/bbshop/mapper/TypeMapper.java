package com.bbshop.mapper;

import com.bbshop.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {

    /**
     * 新增
     */
    int insert(Type type);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Type type);

    /**
     * 根据ID查询
     */
    Type selectById(Integer id);

    /**
     * 查询所有
     */
    List<Type> selectAll(Type type);

}
