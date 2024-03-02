package com.bbshop.service;

import com.bbshop.common.constant.RoleEnum;
import com.bbshop.entity.Account;
import com.bbshop.entity.Comment;
import com.bbshop.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentService{

    public List<Comment> selectAll(Comment comment);

    /**
     * 分页查询
     */
    public PageInfo<Comment> selectPage(Comment comment, Integer pageNum, Integer pageSize) ;

    /**
     * 根据ID查询
     */
    public Comment selectById(Integer id);

    public List<Comment> selectByGoodsId(Integer id);

     /*
     删除
     */
    public void deleteById(Integer id) ;

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) ;

    /**
     *
     * @param comment
     */
    public void add(Comment comment);
}
