package com.bbshop.service;

import cn.hutool.core.date.DateUtil;
import com.bbshop.entity.Account;
import com.bbshop.entity.Notice;
import com.bbshop.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NoticeService {
    /**
     * 新增
     */
    public void add(Notice notice) ;

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
    public void updateById(Notice notice);
    /**
     * 根据ID查询
     */
    public Notice selectById(Integer id) ;

    /**
     * 查询所有
     */
    public List<Notice> selectAll(Notice notice);

    /**
     * 分页查询
     */
    public PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize) ;
}
