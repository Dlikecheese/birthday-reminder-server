package com.xiaoyi.birthdayreminder.controller;

import com.xiaoyi.birthdayreminder.pojo.Result;
import com.xiaoyi.birthdayreminder.pojo.dto.GiftDTO;
import com.xiaoyi.birthdayreminder.pojo.dto.GiftItemDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.PageBean;
import com.xiaoyi.birthdayreminder.service.FileService;
import com.xiaoyi.birthdayreminder.service.GiftService;
import com.xiaoyi.birthdayreminder.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/gift")
@RestController
@Slf4j
public class GiftController {
    @Autowired
    private GiftService giftService;

    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private FileService fileService;

    @PostMapping
    public Result<Void> add(@RequestBody GiftDTO giftDTO){
        log.info("添加礼物");
        try{
            giftService.add(giftDTO);
        }catch(Exception e){
            if(giftDTO.getImage() != null){
                String objectName=  aliOssUtil.getObjectName(giftDTO.getImage());
                aliOssUtil.delete(objectName);
                fileService.deleteByUrl(giftDTO.getImage());
            }
        }
        return Result.success();
    }

    @GetMapping("list")
    public Result<PageBean> list(@RequestParam(defaultValue ="1") Integer start,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 @RequestParam(defaultValue = "false") Boolean isMine,
                                 @RequestParam(defaultValue = "false") Boolean isCollect
                                 )
    {
        log.info("礼物列表");
        PageBean giftList= giftService.list(start,pageSize,isMine,isCollect);
        return Result.success(giftList);
    }

    @PostMapping("/like/{id}/{value}")
    public Result<Void> like(@PathVariable Integer id,@PathVariable String value){
        log.info("喜欢礼物");
        if(Boolean.parseBoolean(value)){
            giftService.like(id);
        }else{
            giftService.cancelLike(id);
        }
        return Result.success();
    }

    @PostMapping("/collect/{id}/{value}")
    public Result<Void> collect(@PathVariable Integer id,@PathVariable String value ){
        log.info("喜欢礼物");

        if(Boolean.parseBoolean(value)){
            giftService.collect(id);
        }else{
            giftService.cancelCollect(id);
        }
        return Result.success();
    }

    @GetMapping("/detail/{id}")
    public Result<GiftItemDTO> detail(@PathVariable Integer id){
        return Result.success( giftService.detail(id));
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Integer id){
        giftService.delete(id);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> update(@RequestBody GiftDTO giftDTO){
        giftService.update(giftDTO);
        return Result.success();
    }

}
