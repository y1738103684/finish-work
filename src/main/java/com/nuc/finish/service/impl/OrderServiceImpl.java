package com.nuc.finish.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuc.finish.common.Pagination;
import com.nuc.finish.dao.OrderMapper;
import com.nuc.finish.dao.UserMapper;
import com.nuc.finish.dao.VideoMapper;
import com.nuc.finish.model.OrderModel;
import com.nuc.finish.model.VideoModel;
import com.nuc.finish.pojo.Order;
import com.nuc.finish.pojo.User;
import com.nuc.finish.pojo.Video;
import com.nuc.finish.service.OrderService;
import com.nuc.finish.service.VideoService;
import com.nuc.finish.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/11 15:47
 */
@Service
public class OrderServiceImpl implements OrderService{
    public static final Integer ORDER_INIT_STATUS = 0;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoService videoService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Boolean isExistOrder(Integer videoId, Integer userId) {
        Order order = orderMapper.selectByVideoIdAndUserId(videoId, userId);
        return order == null ? false : true;
    }

    @Override
    public Boolean buyVideo(User user, Integer videoId, String token) {
        Order order = new Order();
        Video video = videoMapper.selectVideoById(videoId);
        fullOrder(order, user, video);
        int i = orderMapper.insertOrder(order);
        Boolean isVip = user.getIsVip();
        if (isVip) {
            Integer price = video.getPrice();
            int points = user.getVipPoints() + price;
            //添加积分
            int res = userMapper.updatePoints(points, user.getId());
            if (res > 0) {
                JSONObject jsonObject = (JSONObject)redisTemplate.opsForValue().get(token);
                User redisUser = jsonObject.toJavaObject(User.class);
                redisUser.setVipPoints(points);
                redisTemplate.opsForValue().set(token, redisUser);
            }
        }
        return i > 0 ? true : false;
    }

    @Override
    public PageInfo<OrderModel> getSelfVideo(User user, Pagination pagination) {

        PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
        List<Order> orders = orderMapper.listByUserId(user.getId());
        PageInfo<Order> info = new PageInfo<>(orders);
        List<OrderModel> orderModelList = new ArrayList<>();
        for (Order order : orders) {
            Video video = videoService.getVideoById(order.getVideoId());
            VideoModel videoModel = new VideoModel();
            BeanUtils.copyProperties(video, videoModel);
            videoModel.setCreateTime(DateUtil.convertToFormat(video.getCreateTime()));
            User u = userMapper.selectByUserId(video.getUserId());
            videoModel.setUser(u);
            OrderModel model = new OrderModel();
            BeanUtils.copyProperties(order, model);
            model.setVideo(videoModel);
            orderModelList.add(model);
        }
        PageInfo<OrderModel> res = new PageInfo<>();
        BeanUtils.copyProperties(info, res);
        res.setList(orderModelList);
        return res;
    }

    private void fullOrder(Order order, User user, Video video) {
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        order.setOrderTime(date);
        order.setUserId(user.getId());
        order.setVideoId(video.getId());
        order.setOrderStatus(ORDER_INIT_STATUS);
        order.setVideoName(video.getVideoName());
    }
}
