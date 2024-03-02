package com.bbshop.service;

import com.bbshop.entity.Address;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AddressService {

    /**
     * 新增
     */
    public void add(Address address);

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
    public void updateById(Address address);

    /**
     * 根据ID查询
     */
    public Address selectById(Integer id);

    /**
     * 查询所有
     */
    public List<Address> selectAll(Address address) ;
    /**
     * 分页查询
     */
    public PageInfo<Address> selectPage(Address address, Integer pageNum, Integer pageSize);
}
