package com.nuc.finish.dao;

import com.nuc.finish.pojo.Video;
import com.nuc.finish.vo.VideoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/2 22:34
 */
@Mapper
public interface VideoMapper {
    List<Video> listVideo(@Param("type") Integer type);

    Video selectVideoById(@Param("id") Integer id);

    int insertVideo(@Param("vo") VideoVO vo);

    List<Video> listByLimit(@Param("low") Integer low);

    int updatePhotoById(@Param("photo") String photo, @Param("id") Integer id);
}
