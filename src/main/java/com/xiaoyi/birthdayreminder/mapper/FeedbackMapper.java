package com.xiaoyi.birthdayreminder.mapper;

import com.xiaoyi.birthdayreminder.pojo.dto.FeedbackDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedbackMapper {
    List<FeedbackDTO> list(Integer start, Integer pageSize);

    Long count();

    void insert(FeedbackDTO feedbackDTO);
}
