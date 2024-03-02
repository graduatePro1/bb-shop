package com.bbshop.controller;

import com.bbshop.common.ResultBean;
import com.bbshop.entity.Collect;
import com.bbshop.mapper.CollectMapper;
import com.bbshop.service.CollectService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Resource
    private CollectService collectService;

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public ResultBean selectPage(Collect collect,
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Collect> page = collectService.selectPage(collect, pageNum, pageSize);
        return ResultBean.success(page);
    }

    /**
     * 根据id单个删除
     */
    @DeleteMapping("/delete/{id}")
    public ResultBean deleteById(@PathVariable Integer id) {
        collectService.deleteById(id);
        return ResultBean.success();
    }

    /**
     * 加入收藏
     */
    @PostMapping("/add")
    public ResultBean add(@RequestBody Collect collect) {
        collectService.add(collect);
        return ResultBean.success();
    }
}
