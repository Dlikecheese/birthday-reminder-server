package com.xiaoyi.birthdayreminder.mapper;

import com.xiaoyi.birthdayreminder.pojo.dto.GiftItemDTO;
import com.xiaoyi.birthdayreminder.pojo.dto.GiftLikeDTO;
import com.xiaoyi.birthdayreminder.pojo.entity.Gift;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GiftMapper {
    void insert(Gift gift);

    List<GiftItemDTO> listByCollect(Integer start,Integer pageSize,String creatorId);

    Long count(String creatorId, boolean isMine);

    List<GiftItemDTO> list(Integer start, Integer pageSize, String creatorId,Boolean isMine);

    @Select("SELECT COUNT(*) FROM tb_gift_collect WHERE user_id = #{creatorId}")
    Long countByCollect(String creatorId);

    void like(GiftLikeDTO giftLikeDTO);

    @Delete("delete from tb_gift_favorite where gift_id = #{id} and user_id=#{creator}")
    void deleteLikeId(Integer id,String creator);

    void collect(GiftLikeDTO giftLikeDTO);

    @Delete("delete from tb_gift_collect where gift_id = #{id} and user_id=#{creator}")
    void deleteCollectId(Integer id,String creator);


    @Select("select * from tb_gift where id = #{id}")
    GiftItemDTO detail(Integer id);

    @Delete("delete from tb_gift where id=#{id}")
    void delete(Integer id);

    void update(Gift gift);

    // 删除收藏表里giftId为id的记录
    @Delete("delete from tb_gift_collect where id =#{id}")
    void deleteCollectById(Integer id);

    // 删除喜爱表里giftId为id的记录
    @Delete("delete from tb_gift_favorite where id =#{id}")
    void deleteLikeById(Integer id);
}
