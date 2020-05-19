package com.nuc.finish.dao;

import com.nuc.finish.common.Pagination;
import com.nuc.finish.pojo.Gift;
import com.nuc.finish.vo.GiftVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/24 16:06
 */
@Mapper
public interface GiftMapper {
    List<Gift> listGifts();

    int insertGift(@Param("vo") GiftVO vo);

    int deleteGiftById(@Param("id") Integer id);
}
