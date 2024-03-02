package com.bbshop.controller;

import com.bbshop.common.ResultBean;
import com.bbshop.entity.Cart;
import com.bbshop.service.CartService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private CartService cartService;

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public ResultBean selectPage(Cart cart,
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Cart> page = cartService.selectPage(cart, pageNum, pageSize);
        return ResultBean.success(page);
    }

    /**
     * 加入购物车
     */
    @PostMapping("/add")
    public ResultBean add(@RequestBody Cart cart) {
        cartService.add(cart);
        return ResultBean.success();
    }

    /**
     * 删除购物车商品
     */
    @DeleteMapping("/delete/{id}")
    public ResultBean deleteById(@PathVariable Integer id) {
        cartService.deleteById(id);
        return ResultBean.success();
    }
}
