package com.bbshop.controller;

import com.bbshop.common.ResultBean;
import com.bbshop.entity.Type;
import com.bbshop.service.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/type")
@RestController
public class TypeController {

    @Resource
    private TypeService typeService;

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public ResultBean selectAll(Type type ) {
        List<Type> list = typeService.selectAll(type);
        return ResultBean.success(list);
    }
}
