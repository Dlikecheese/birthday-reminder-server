package com.xiaoyi.birthdayreminder.controller;


import com.xiaoyi.birthdayreminder.pojo.Result;
import com.xiaoyi.birthdayreminder.pojo.dto.BirthdayDTO;
import com.xiaoyi.birthdayreminder.pojo.dto.BirthdayItemDTO;
import com.xiaoyi.birthdayreminder.service.BirthdayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/birthday")
public class BirthdayController {
    @Autowired
    private  BirthdayService birthdayService;

    @PostMapping
    public Result<Void> add(@RequestBody BirthdayDTO birthdayDTO){
        log.info(birthdayDTO.toString());
        birthdayService.add(birthdayDTO);
        return Result.success();
    }

    @GetMapping
    public Result<List<BirthdayItemDTO>> list(){
      List<BirthdayItemDTO> birthdayList = birthdayService.list();
        return Result.success(birthdayList);
    }
}
