package com.xiaoyi.birthdayreminder.controller;


import com.xiaoyi.birthdayreminder.pojo.Result;
import com.xiaoyi.birthdayreminder.pojo.dto.BirthdayDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.Birthday;
import com.xiaoyi.birthdayreminder.service.BirthdayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/birthday")
public class BirthdayController {
    @Autowired
    private  BirthdayService birthdayService;

//    @GetMapping
//    public Result list(){
////        log.info("查询全部部门数据");
//
////       调用service
//        return Result.success();
//    }

    @PostMapping
    public Result<Void> add(@RequestBody BirthdayDTO birthdayDTO){
        log.info(birthdayDTO.toString());
        birthdayService.add(birthdayDTO);
        return Result.success();
    }
}
