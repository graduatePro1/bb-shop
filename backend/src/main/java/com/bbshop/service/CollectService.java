package com.bbshop.service;

import cn.hutool.core.util.ObjectUtil;
import com.bbshop.common.constant.RoleEnum;
import com.bbshop.entity.Account;
import com.bbshop.entity.Collect;
import com.bbshop.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CollectService {
    public void add(Collect collect);

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
    public void updateById(Collect collect);

    /**
     * 根据ID查询
     */
    public Collect selectById(Integer id);

    /**
     * 查询所有
     */
    public List<Collect> selectAll(Collect collect);

    /**
     * 分页查询
     */
    public PageInfo<Collect> selectPage(Collect collect, Integer pageNum, Integer pageSize) ;
}
