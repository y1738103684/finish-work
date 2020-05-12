package com.nuc.finish.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/24 16:07
 */
@Data
public class Gift {
    private Integer id;
    private String name;
    private Integer points;
    private String photo;
    private Date updateTime;
    private Date createTime;
}
