package com.xiaoyi.birthdayreminder.service.impl;

import com.xiaoyi.birthdayreminder.context.BaseContext;
import com.xiaoyi.birthdayreminder.mapper.BirthdayMapper;
import com.xiaoyi.birthdayreminder.pojo.dto.BirthdayDTO;
import com.xiaoyi.birthdayreminder.pojo.dto.BirthdayItemDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.Birthday;
import com.xiaoyi.birthdayreminder.service.BirthdayService;
import org.springframework.beans.BeanUtils;
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
        return birthdayMapper.list(creatorId);
    }

    @Override
    public BirthdayDTO getById(String id) {
        Birthday birthday = birthdayMapper.getById(id);

        BirthdayDTO birthdayDTO = new BirthdayDTO();
        birthdayDTO.setName(birthday.getName());
        birthdayDTO.setBirthday(birthday.getBirthday());
        if(!birthday.getRemindTime().isEmpty()){
            String[] reminderTimes = birthday.getRemindTime().split(",");
            birthdayDTO.setRemindTime(reminderTimes);
        }else{
            birthdayDTO.setRemindTime(new String[0]);
        }
        if(birthdayDTO.getRelation()!=null){
            birthdayDTO.setRelation(String.valueOf(birthday.getRelation()));
        }
        birthdayDTO.setSex(birthday.getSex());
        birthdayDTO.setAddress(birthday.getAddress());
        birthdayDTO.setPhone(birthday.getPhone());
        birthdayDTO.setComment(birthday.getComment());
        birthdayDTO.setTag(birthday.getTag());
        return birthdayDTO;
    }

    @Override
    public void updateById(BirthdayDTO birthdayDTO) {
        Birthday birthday = new Birthday();
        BeanUtils.copyProperties(birthdayDTO,birthday);

        if (birthdayDTO.getRemindTime() != null) {
            String reminderTimeStr = String.join(",", Arrays.asList(birthdayDTO.getRemindTime()));
            birthday.setRemindTime(reminderTimeStr);
        }
        birthday.setUpdateTime(LocalDateTime.now());

        birthdayMapper.updateById(birthday);
    }

    @Override
    public void delById(String id) {
        birthdayMapper.delById(id);
    }
}
