package com.nuc.finish.model;

import com.nuc.finish.pojo.User;
import lombok.Data;

import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/6 16:08
 */
@Data
public class VideoModel {
    private Integer id;
    private Integer videoType;
    private String videoName;
    private String videoDesc;
    private User user;
    private Integer playNumber;
    private String address;
    private Date lastPlayTime;
    private Integer price;
    private Date updateTime;
    private String createTime;
    private String photo;
}
