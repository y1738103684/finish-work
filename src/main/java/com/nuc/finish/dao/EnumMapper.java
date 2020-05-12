package com.nuc.finish.dao;

import com.nuc.finish.pojo.BaseEnum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/10 15:28
 */
@Mapper
public interface EnumMapper {
    List<BaseEnum> getVideoType();
}
