package com.xiaoyi.birthdayreminder.service;


import com.xiaoyi.birthdayreminder.pojo.dto.GiftDTO;
import com.xiaoyi.birthdayreminder.pojo.dto.GiftItemDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.PageBean;

import java.util.List;

public interface GiftService {
    void add(GiftDTO giftDTO);

    PageBean list(Integer start,Integer pageSize,boolean isMine,boolean isCollect);

    void like(Integer id);

    void collect(Integer id);

    void cancelLike(Integer id);

    void cancelCollect(Integer id);
}
