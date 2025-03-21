package com.xiaoyi.birthdayreminder.service;

import com.xiaoyi.birthdayreminder.pojo.dto.BirthdayDTO;
import com.xiaoyi.birthdayreminder.pojo.dto.BirthdayItemDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface BirthdayService {
    void add(BirthdayDTO birthdayDTO);

    List<BirthdayItemDTO> list();
}
