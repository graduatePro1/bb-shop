package com.bbshop.controller;

import com.bbshop.common.ResultBean;
import com.bbshop.entity.Address;
import com.bbshop.service.AddressService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressService;

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public ResultBean selectPage(Address address,
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Address> page = addressService.selectPage(address, pageNum, pageSize);
        return ResultBean.success(page);
    }

    /**
     * 查询用户全部的地址
     */

    @RequestMapping("/selectAll")
    public ResultBean selectAll(Address address){
        List<Address> list=addressService.selectAll(address);
        return ResultBean.success(list);
    }

    /**
            * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public ResultBean selectById(@PathVariable Integer id) {
        Address address = addressService.selectById(id);
        return ResultBean.success(address);
    }
    /**
     * 新增
     */
    @PostMapping("/add")
    public ResultBean add(@RequestBody Address address) {
        System.out.println(address);
        addressService.add(address);
        return ResultBean.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public ResultBean updateById(@RequestBody Address address) {
        addressService.updateById(address);
        return ResultBean.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResultBean deleteById(@PathVariable Integer id) {
        addressService.deleteById(id);
        return ResultBean.success();
    }
}
