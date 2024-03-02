package com.bbshop.controller;

import com.bbshop.common.ResultBean;
import com.bbshop.entity.Orders;
import com.bbshop.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Resource
    private OrdersService ordersService;

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public ResultBean selectAll(Orders orders ) {
        List<Orders> list = ordersService.selectAll(orders);
        return ResultBean.success(list);
    }

    /**
     * 分页查询、按订单号查询
     */
    @GetMapping("/selectPage")
    public ResultBean selectPage(Orders orders,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        System.out.println("/n"+orders+"/n");
        PageInfo<Orders> page = ordersService.selectPage(orders, pageNum, pageSize);
        return ResultBean.success(page);
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResultBean deleteById(@PathVariable Integer id) {
        ordersService.deleteById(id);
        return ResultBean.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public ResultBean deleteBatch(@RequestBody List<Integer> ids) {
        ordersService.deleteBatch(ids);
        return ResultBean.success();
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    public ResultBean add(@RequestBody Orders orders) {
        ordersService.add(orders);
        return ResultBean.success();
    }
}
