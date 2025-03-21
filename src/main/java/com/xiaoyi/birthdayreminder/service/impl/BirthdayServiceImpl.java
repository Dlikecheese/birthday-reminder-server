package com.xiaoyi.birthdayreminder.service.impl;

import com.xiaoyi.birthdayreminder.context.BaseContext;
import com.xiaoyi.birthdayreminder.mapper.BirthdayMapper;
import com.xiaoyi.birthdayreminder.pojo.dto.BirthdayDTO;
import com.xiaoyi.birthdayreminder.pojo.dto.BirthdayItemDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.Birthday;
import com.xiaoyi.birthdayreminder.service.BirthdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class BirthdayServiceImpl implements BirthdayService {
    @Autowired
    private BirthdayMapper birthdayMapper;

    @Override
    public void add(BirthdayDTO birthdayDTO) {
        Birthday birthday = new Birthday();
        birthday.setName(birthdayDTO.getName());
        birthday.setBirthday(birthdayDTO.getBirthday());
        birthday.setRelation(birthdayDTO.getRelation());
        birthday.setSex(birthdayDTO.getSex());
        birthday.setAddress(birthdayDTO.getAddress());
        birthday.setPhone(birthdayDTO.getPhone());
        birthday.setComment(birthdayDTO.getComment());
        birthday.setTag(birthdayDTO.getTag());

        if (birthdayDTO.getRemindTime() != null) {
            String reminderTimeStr = String.join(",", Arrays.asList(birthdayDTO.getRemindTime()));
            birthday.setRemindTime(reminderTimeStr);
        }

        birthday.setCreateTime(LocalDateTime.now());
        birthday.setUpdateTime(LocalDateTime.now());
        String creatorId = BaseContext.getCurrentId();
        birthday.setCreator(creatorId);

        birthdayMapper.insert(birthday);

    }

    @Override
    public List<BirthdayItemDTO> list() {
        String creatorId = BaseContext.getCurrentId();
        List<BirthdayItemDTO> birthdayList =   birthdayMapper.list(creatorId);
        return birthdayList;
    }
}
