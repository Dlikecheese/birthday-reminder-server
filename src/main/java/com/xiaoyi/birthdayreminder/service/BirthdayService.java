package com.xiaoyi.birthdayreminder.service;

import com.xiaoyi.birthdayreminder.pojo.dto.BirthdayDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.Birthday;
import org.springframework.stereotype.Service;

@Service
public interface BirthdayService {
    void add(BirthdayDTO birthdayDTO);
}
