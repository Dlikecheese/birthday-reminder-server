package com.xiaoyi.birthdayreminder.service.impl;

import com.xiaoyi.birthdayreminder.context.BaseContext;
import com.xiaoyi.birthdayreminder.mapper.FeedbackMapper;
import com.xiaoyi.birthdayreminder.pojo.dto.FeedbackDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.PageBean;
import com.xiaoyi.birthdayreminder.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public void insert(FeedbackDTO feedbackDTO) {
        feedbackDTO.setUser(BaseContext.getCurrentId());
        feedbackDTO.setCreateTime(LocalDateTime.now());
        feedbackMapper.insert(feedbackDTO);
    }

    @Override
    public PageBean list(Integer start, Integer pageSize) {
        List<FeedbackDTO> feedbackDTOs = feedbackMapper.list(start, pageSize);
        Long count = feedbackMapper.count();
        return new PageBean(count, feedbackDTOs);
    }
}
