package com.bbshop.controller;

import com.bbshop.common.ResultBean;
import com.bbshop.entity.Comment;
import com.bbshop.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public ResultBean selectAll(Comment comment ) {
        List<Comment> list = commentService.selectAll(comment);
        return ResultBean.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public ResultBean selectPage(Comment comment,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Comment> page = commentService.selectPage(comment, pageNum, pageSize);
        return ResultBean.success(page);
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResultBean deleteById(@PathVariable Integer id) {
        commentService.deleteById(id);
        return ResultBean.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public ResultBean deleteBatch(@RequestBody List<Integer> ids) {
        commentService.deleteBatch(ids);
        return ResultBean.success();
    }

    /**
     * 查询商品的评论信息
     */
    @RequestMapping("/selectByGoodsId")
    public ResultBean selectById(@RequestParam Integer id){
        List<Comment> comments = commentService.selectByGoodsId(id);
        return ResultBean.success(comments);

    }

    /**
     * 用户评价
     */
    @PostMapping("/add")
    public ResultBean add(@RequestBody Comment comment) {
        commentService.add(comment);
        return ResultBean.success();
    }


}
