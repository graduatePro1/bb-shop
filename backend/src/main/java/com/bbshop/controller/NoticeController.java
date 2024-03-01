package com.bbshop.controller;

import com.bbshop.common.ResultBean;
import com.bbshop.entity.Notice;
import com.bbshop.service.NoticeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @RequestMapping("/selectAll")
    public ResultBean selectAll(Notice notice){
        List<Notice> notices = noticeService.selectAll(notice);
        return ResultBean.success(notices);
    }


}
