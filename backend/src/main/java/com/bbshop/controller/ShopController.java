package com.bbshop.controller;

import cn.hutool.core.util.ObjectUtil;
import com.bbshop.common.ResultBean;
import com.bbshop.common.constant.CodeEnum;
import com.bbshop.entity.Account;
import com.bbshop.entity.Shop;
import com.bbshop.entity.User;
import com.bbshop.service.BaseService;
import com.bbshop.service.ShopService;
import com.bbshop.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.AccessibleObject;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private ShopService shopService;

    @Resource
    private BaseService baseService;

    @PutMapping("/update")
    public ResultBean update(@RequestBody Account account){
        Shop shop=new Shop();
        BeanUtils.copyProperties(account,shop);
        if(account.isEmailState() && ObjectUtil.isEmpty(account.getCode())){
            return ResultBean.error(CodeEnum.PARAM_LOST_ERROR);
        }
        if(ObjectUtil.isEmpty(shop)){
            return ResultBean.error(CodeEnum.PARAM_LOST_ERROR);
        }
        shopService.updateById(shop,account.getCode(),account.isEmailState());
        return ResultBean.success();

    }

    /**
     * 根据ID查询店铺信息
     */
    @GetMapping("/selectById/{id}")
    public ResultBean selectById(@PathVariable Integer id) {
        Shop shop = shopService.selectById(id);
        return ResultBean.success(shop);
    }
}
