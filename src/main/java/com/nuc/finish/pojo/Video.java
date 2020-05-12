package com.nuc.finish.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/2 22:35
 */
@Data
public class Video {
    private Integer id;
    private Integer videoType;
    private String videoName;
    private String videoDesc;
    private Integer userId;
    private Integer playNumber;
    private String address;
    private Date lastPlayTime;
    private Integer price;
    private Date updateTime;
    private Date createTime;
    private String photo;
}
