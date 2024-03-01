package com.bbshop.controller;

import com.bbshop.common.ResultBean;
import com.bbshop.entity.Goods;
import com.bbshop.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;
    /**
     * 查询所有/按条件查询
     */
    @GetMapping("/selectAll")
    public ResultBean selectAll(Goods goods ) {
        List<Goods> list = goodsService.selectAll(goods);

        return ResultBean.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public ResultBean selectPage(Goods goods,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        System.out.println(goods);
        PageInfo<Goods> page = goodsService.selectPage(goods, pageNum, pageSize);
        return ResultBean.success(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    public ResultBean add(@RequestBody Goods goods) {
        goodsService.add(goods);
        return ResultBean.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public ResultBean updateById(@RequestBody Goods goods) {
        goodsService.updateById(goods);
        return ResultBean.success();
    }

    /**
     * 单个删除
     */
    @DeleteMapping("/delete/{id}")
    public ResultBean deleteById(@PathVariable Integer id) {
        goodsService.deleteById(id);
        return ResultBean.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public ResultBean deleteBatch(@RequestBody List<Integer> ids) {
        goodsService.deleteBatch(ids);
        return ResultBean.success();
    }

    /**
     * 热卖商品显示，查询数据库中的前15条商品
     * @return
     */
    @GetMapping("/selectTop15")
    public ResultBean selectTop15() {
        List<Goods> list = goodsService.selectTop15();
        return ResultBean.success(list);
    }

    /**
     * 查询某个商品的详情页
     * @param id
     * @return
     */
    @GetMapping("/selectById")
    public ResultBean selectById(@RequestParam Integer id) {
        Goods goods = goodsService.selectById(id);
        return ResultBean.success(goods);
    }

    /**
     * 根据商品名字查询
     * @param goods
     * @return
     */
    @GetMapping("/selectByNamePage")
    public ResultBean selectByName(Goods goods,
                                   @RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        //        封装分页工具
        PageInfo<Goods> goodsPageInfo = goodsService.selectPage(goods, pageNum, pageSize);
        return ResultBean.success(goodsPageInfo);
    }
}
