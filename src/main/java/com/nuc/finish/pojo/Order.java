package com.nuc.finish.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/11 15:37
 */
@Data
public class Order {
    private Integer id;
    private Integer videoId;
    private String videoName;
    private Integer userId;
    private Integer orderStatus;
    private Date orderTime;
    private Date createTime;
    private Date updateTime;
}
