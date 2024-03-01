package com.bbshop.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.bbshop.common.ResultBean;
import com.bbshop.common.constant.CodeEnum;
import com.bbshop.common.constant.RoleEnum;
import com.bbshop.entity.Account;
import com.bbshop.entity.User;
import com.bbshop.service.BaseService;
import com.bbshop.service.ShopService;
import com.bbshop.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

//一级请求接口
@RestController
public class BaseController {

    @Resource
    private UserService userService;
    @Resource
    private ShopService shopService;
    @Resource
    private BaseService baseService;

    @GetMapping("/")
    public ResultBean hello() {
        return ResultBean.success("访问成功");
    }


    @PostMapping("/login")
    public ResultBean login(@RequestBody Account account){
        //        判断是否缺失登录参数
        if (
                (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword()))
                && (ObjectUtil.isEmpty(account.getEmail()) || ObjectUtil.isEmpty(account.getCode()))
                || ObjectUtil.isEmpty(account.getRole())) {
            return ResultBean.error(CodeEnum.PARAM_LOST_ERROR);
        }
            account = baseService.login(account);
            return ResultBean.success(account);

    }
//发送邮件
    @PostMapping("/sendCode")
    public ResultBean sendCode(@RequestBody Account account){
        if(ObjectUtil.isEmpty(account.getEmail())){
            return ResultBean.error(CodeEnum.PARAM_LOST_ERROR);
        }
        String result = baseService.sendEmailCode(account.getEmail(), account.isEmailState(),account.getRole());

        if ("验证码发送成功".equals(result)) {
            return ResultBean.success();
        } else if ("验证码发送失败，请联系管理员".equals(result)) {
            return ResultBean.error(CodeEnum.SYSTEM_ERROR);
        } else {
            return ResultBean.error("5008",result);
        }
    }

    //校验验证码
    @PostMapping("/compareCode")
    public ResultBean checkCode(@RequestBody Account account,HttpSession session){
        if(ObjectUtil.isEmpty(account.getEmail())){
            return ResultBean.error(CodeEnum.PARAM_LOST_ERROR);
        }
        String result = baseService.checkCode(account.getEmail(), account.getCode(),account.getRole());
        if(result.equals("验证成功")){
            session.setAttribute("reset-password",account.getEmail());
            String email= (String) session.getAttribute("reset-password");
            System.out.println(email);
            return ResultBean.success();
        }
        return ResultBean.error(CodeEnum.SYSTEM_ERROR);
    }

    //忘记密码
    @PostMapping("/forgetPassword")
    public ResultBean modifyPassword(@RequestBody Account account,HttpSession session){
        //判断是否有密码参数
        if(ObjectUtil.isEmpty(account.getPassword())){
            return ResultBean.error(CodeEnum.PARAM_LOST_ERROR);
        }
        //获取存放在session里的邮箱
        String email= (String) session.getAttribute("reset-password");
        System.out.println(email);
        User user= new User();
        user.setEmail(email);
        user.setPassword(account.getPassword());
        if(userService.updateByEmail(user)>0){
            session.removeAttribute("reset-password");
            return ResultBean.success();
        }
        return ResultBean.error(CodeEnum.SYSTEM_ERROR);
    }

    /**
     * 修改密码
     */
    @PutMapping("/updatePassword")
    public ResultBean updatePassword(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getNewPassword())) {
            return ResultBean.error(CodeEnum.PARAM_LOST_ERROR); //任何一个参数缺失返回错误信息
        }
        baseService.updatePassword(account);

        return ResultBean.success();
    }
    /**
     * 注册
     */
    @PostMapping("/register")
    public ResultBean register(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())||ObjectUtil.isEmpty(account.getEmail())||
        ObjectUtil.isEmpty(account.getCode())||ObjectUtil.isEmpty(account.getPhone())) {
            return ResultBean.error(CodeEnum.PARAM_LOST_ERROR);
        }


        baseService.register(account);

        return ResultBean.success();
    }

}
