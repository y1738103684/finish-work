package com.nuc.finish.service;

import com.github.pagehelper.PageInfo;
import com.nuc.finish.common.Pagination;
import com.nuc.finish.model.VideoModel;
import com.nuc.finish.pojo.Video;
import com.nuc.finish.vo.VideoVO;

import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/2 22:35
 */
public interface VideoService {
    PageInfo<VideoModel> showVideo(Pagination pagination, Integer type);

    Video getVideoById(Integer id);

    int uploadVideo(VideoVO vo);

    List<Video> recommend();

    int uploadPhoto(VideoVO vo);
}
