package com.nuc.finish.service;

import com.github.pagehelper.PageInfo;
import com.nuc.finish.common.Pagination;
import com.nuc.finish.model.OrderModel;
import com.nuc.finish.pojo.User;
import com.nuc.finish.pojo.Video;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/11 15:42
 */
public interface OrderService {
    Boolean isExistOrder(Integer videoId, Integer userId);

    Boolean buyVideo(User user, Integer videoId, String token);

    PageInfo<OrderModel> getSelfVideo(User user, Pagination pagination);
}
