package com.nuc.finish.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/24 16:03
 */
@Data
public class GiftVO {
    private Integer id;
    private String name;
    private Integer points;
    private String photo;
    private Date updateTime;
    private Date createTime;
}
