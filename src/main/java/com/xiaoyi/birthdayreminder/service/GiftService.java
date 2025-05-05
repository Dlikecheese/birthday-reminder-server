package com.xiaoyi.birthdayreminder.service;


import com.xiaoyi.birthdayreminder.pojo.dto.GiftDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.PageBean;

public interface GiftService {
    void add(GiftDTO giftDTO);

    PageBean list(Integer start,Integer pageSize,boolean isMine,boolean isCollect);

    void like(Integer id);

    void collect(Integer id);

    void cancelLike(Integer id);

    void cancelCollect(Integer id);

    GiftDTO detail(Integer id);

    void delete(Integer id);

    void update(GiftDTO giftDTO);
}
