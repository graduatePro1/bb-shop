package com.bbshop.service;

import com.bbshop.common.constant.RoleEnum;
import com.bbshop.entity.Account;
import com.bbshop.entity.Goods;
import com.bbshop.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface GoodsService {
    /**
     * 新增
     */
    public void add(Goods goods);

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
    public void updateById(Goods goods);

    /**
     * 根据ID查询
     */
    public Goods selectById(Integer id);

    /**
     * 查询所有
     */
    public List<Goods> selectAll(Goods goods);

    /**
     * 分页查询
     */
    public PageInfo<Goods> selectPage(Goods goods, Integer pageNum, Integer pageSize);
    public List<Goods> selectTop15();

    public List<Goods> selectByTypeId(Integer id);

    public List<Goods> selectByBusinessId(Integer id);

    public List<Goods> selectByName(String name);


    public List<Goods> getRandomGoods(int num);
}
