package com.xiaoyi.birthdayreminder.service;

import com.xiaoyi.birthdayreminder.pojo.dto.FeedbackDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.PageBean;
import org.springframework.stereotype.Service;

@Service
public interface FeedbackService {
    PageBean list(Integer start, Integer pageSize);

    void insert(FeedbackDTO feedbackDTO);
}
