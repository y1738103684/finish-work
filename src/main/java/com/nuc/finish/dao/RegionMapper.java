package com.nuc.finish.dao;

import com.nuc.finish.pojo.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/20 15:43
 */
@Mapper
public interface RegionMapper {
    List<Region> listByParentId(@Param("parentId") Integer parentId);

    Region selectById(@Param("id") Integer id);
}
