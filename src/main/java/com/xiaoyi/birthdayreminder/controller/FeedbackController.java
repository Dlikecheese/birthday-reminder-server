package com.xiaoyi.birthdayreminder.controller;

import com.xiaoyi.birthdayreminder.pojo.Result;
import com.xiaoyi.birthdayreminder.pojo.dto.FeedbackDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.PageBean;
import com.xiaoyi.birthdayreminder.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/feedback")
@RestController
@Slf4j
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("list")
    public Result<PageBean> list(@RequestParam(defaultValue = "1") Integer start,
                                 @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        PageBean feedbackList = feedbackService.list(start, pageSize);
        return Result.success(feedbackList);
    }

    @PostMapping("/add")
    public Result<Void> feedback(@RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.insert(feedbackDTO);
        return Result.success();
    }
}
