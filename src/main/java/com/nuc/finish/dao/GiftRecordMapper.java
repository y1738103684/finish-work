package com.nuc.finish.dao;

import com.nuc.finish.vo.GiftRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/27 18:22
 */
@Mapper
public interface GiftRecordMapper {
    int insertGiftRecord(@Param("vo")GiftRecordVO vo);
}
