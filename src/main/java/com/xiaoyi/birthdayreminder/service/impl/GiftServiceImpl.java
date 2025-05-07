package com.xiaoyi.birthdayreminder.service.impl;

import com.xiaoyi.birthdayreminder.context.BaseContext;
import com.xiaoyi.birthdayreminder.mapper.GiftMapper;
import com.xiaoyi.birthdayreminder.pojo.dto.GiftDTO;
import com.xiaoyi.birthdayreminder.pojo.dto.GiftItemDTO;
import com.xiaoyi.birthdayreminder.pojo.dto.GiftLikeDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.Gift;
import com.xiaoyi.birthdayreminder.pojo.entity.PageBean;
import com.xiaoyi.birthdayreminder.service.FileService;
import com.xiaoyi.birthdayreminder.service.GiftService;
import com.xiaoyi.birthdayreminder.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class GiftServiceImpl implements GiftService {
    @Autowired
    private GiftMapper giftMapper;

    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private FileService fileService;

    @Override
    public void add(GiftDTO giftDTO) {
        Gift gift = new Gift();
        BeanUtils.copyProperties(giftDTO, gift);
        gift.setCreateTime(LocalDateTime.now());
        gift.setUpdateTime(LocalDateTime.now());
        String creator = BaseContext.getCurrentId();
        gift.setCreator(creator);

        String usageAge = String.join(",",giftDTO.getUsageAge());
        gift.setUsageAge(usageAge);

        giftMapper.insert(gift);
    }

    @Override
    public PageBean list(Integer start,Integer pageSize,boolean isMine,boolean isCollect) {
        String creatorId = BaseContext.getCurrentId();
        List<GiftItemDTO> giftList;
        Long count;

        if(isCollect){
            giftList = giftMapper.listByCollect(start, pageSize, creatorId);
            count = giftMapper.countByCollect(creatorId);
        }else{
            giftList = giftMapper.list(start, pageSize, creatorId,isMine);
            count = giftMapper.count(creatorId,isMine);
        }

        return new PageBean(count, giftList);
    }

    @Override
    public void like(Integer id) {
        GiftLikeDTO giftLikeDTO = new GiftLikeDTO();
        giftLikeDTO.setGiftId(id);
        giftLikeDTO.setCreateTime(LocalDateTime.now());
        giftLikeDTO.setUserId(BaseContext.getCurrentId());
        giftMapper.like(giftLikeDTO);
    }

    @Override
    public void collect(Integer id) {
        GiftLikeDTO giftLikeDTO = new GiftLikeDTO();
        giftLikeDTO.setGiftId(id);
        giftLikeDTO.setCreateTime(LocalDateTime.now());
        giftLikeDTO.setUserId(BaseContext.getCurrentId());
        giftMapper.collect(giftLikeDTO);
    }

    @Override
    public void cancelLike(Integer id) {
        String creatorId = BaseContext.getCurrentId();
        giftMapper.deleteLikeId(id,creatorId);
    }

    @Override
    public void cancelCollect(Integer id) {
        String creatorId = BaseContext.getCurrentId();
        giftMapper.deleteCollectId(id,creatorId);
    }

    @Override
    public GiftDTO detail(Integer id) {
        GiftItemDTO giftItemDTO =  giftMapper.detail(id);
        GiftDTO giftDTO = new GiftDTO();

        BeanUtils.copyProperties(giftItemDTO,giftDTO);

        String[] usageAge = giftItemDTO.getUsageAge().split(",");
        giftDTO.setUsageAge(usageAge);

        Boolean isMine = giftItemDTO.getCreator().equals(BaseContext.getCurrentId());
        giftDTO.setIsMine(isMine);
        return giftDTO;
    }

    @Override
    public void delete(Integer id) {
        GiftDTO giftDTO = this.detail(id);
        if(giftDTO.getCreator().equals(BaseContext.getCurrentId())){
            String image=giftDTO.getImage();
            if(image !=null){
                String objectName=  aliOssUtil.getObjectName(image);
                aliOssUtil.delete(objectName);
                fileService.deleteByUrl(image);
            }

            giftMapper.delete(id);
            giftMapper.deleteCollectById(id);
            giftMapper.deleteLikeById(id);
        }
    }

    @Override
    public void update(GiftDTO giftDTO) {
        Gift gift = new Gift();
        gift.setName(giftDTO.getName());
        gift.setDescription(giftDTO.getDescription());
        gift.setImage(giftDTO.getImage());
        gift.setUsageSex(giftDTO.getUsageSex());

        String usageAge = String.join(",",giftDTO.getUsageAge());
        gift.setUsageAge(usageAge);

        GiftDTO giftInfo = this.detail(giftDTO.getId());
        gift.setId(giftInfo.getId());
        if(giftInfo.getCreator().equals(BaseContext.getCurrentId())){
            gift.setUpdateTime(LocalDateTime.now());
            giftMapper.update(gift);
        }
    }
}
