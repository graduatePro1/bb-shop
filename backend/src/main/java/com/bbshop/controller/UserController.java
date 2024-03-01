package com.bbshop.controller;

import cn.hutool.core.util.ObjectUtil;
import com.bbshop.common.ResultBean;
import com.bbshop.common.constant.CodeEnum;
import com.bbshop.entity.Account;
import com.bbshop.entity.User;
import com.bbshop.service.BaseService;
import com.bbshop.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.AccessibleObject;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private BaseService baseService;

    @PutMapping("/update")
    public ResultBean update(@RequestBody Account account){
        User user=new User();
        BeanUtils.copyProperties(account,user);
        if(account.isEmailState() && ObjectUtil.isEmpty(account.getCode())){
            return ResultBean.error(CodeEnum.PARAM_LOST_ERROR);
        }
        if(ObjectUtil.isEmpty(user)){
            return ResultBean.error(CodeEnum.PARAM_LOST_ERROR);
        }
        userService.updateById(user,account.getCode(),account.isEmailState());
        return ResultBean.success();

    }
}
