package com.bbshop.service;

import com.bbshop.entity.Type;
import com.bbshop.mapper.TypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TypeService {

    /**
     * 新增
     */
    public void add(Type type);

    /**
     * 删除
     */
    public void deleteById(Integer id);

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids);


    /**
     * 修改
     */
    public void updateById(Type type);

    /**
     * 根据ID查询
     */
    public Type selectById(Integer id);

    /**
     * 查询所有
     */
    public List<Type> selectAll(Type type);

    /**
     * 分页查询
     */
    public PageInfo<Type> selectPage(Type type, Integer pageNum, Integer pageSize);
}
