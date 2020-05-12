package com.nuc.finish.model;

import com.nuc.finish.pojo.User;
import com.nuc.finish.pojo.Video;
import lombok.Data;

import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/11 17:56
 */
@Data
public class OrderModel {
    private Integer id;
    private VideoModel video;
    private String videoName;
    private User user;
    private Integer orderStatus;
    private Date orderTime;
    private Date createTime;
    private Date updateTime;
}
