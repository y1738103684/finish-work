package com.nuc.finish.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuc.finish.common.Pagination;
import com.nuc.finish.dao.UserMapper;
import com.nuc.finish.dao.VideoMapper;
import com.nuc.finish.model.VideoModel;
import com.nuc.finish.pojo.User;
import com.nuc.finish.pojo.Video;
import com.nuc.finish.service.VideoService;
import com.nuc.finish.util.DateUtil;
import com.nuc.finish.vo.VideoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/2 22:36
 */
@Service
public class VideoServiceImpl implements VideoService {

    public static final Integer NUMBER_SIX = 6;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<VideoModel> showVideo( Pagination pagination,  Integer type) {
        PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
        List<Video> videos = videoMapper.listVideo(type);

        PageInfo<Video> info = new PageInfo<>(videos);
        List<VideoModel> list = new ArrayList<>();
        for (Video video : videos) {
            VideoModel model = convertVideoMode(video);
            String createDate = DateUtil.convertToFormat(video.getCreateTime());
            model.setCreateTime(createDate);
            User user = userMapper.selectByUserId(video.getUserId());
            model.setUser(user);
            list.add(model);
        }
        PageInfo<VideoModel> res = new PageInfo<>();
        BeanUtils.copyProperties(info, res);
        res.setList(list);
        return res;
    }

    @Override
    public Video getVideoById(Integer id) {
        return videoMapper.selectVideoById(id);
    }

    @Override
    public int uploadVideo(VideoVO vo) {
        int i = videoMapper.insertVideo(vo);
        if (i > 0) {
            return vo.getId();
        } else {
            return -1;
        }
    }

    @Override
    public List<Video> recommend() {
        List<Video> videos = videoMapper.listByLimit(NUMBER_SIX);
        return videos;
    }

    @Override
    public int uploadPhoto(VideoVO vo) {
        int i = videoMapper.updatePhotoById(vo.getPhoto(), vo.getId());
        return i;
    }

    private VideoModel convertVideoMode(Video video) {
        VideoModel model = new VideoModel();
        BeanUtils.copyProperties(video, model);
        return model;
    }
}
