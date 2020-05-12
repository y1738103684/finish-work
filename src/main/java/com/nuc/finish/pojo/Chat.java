package com.nuc.finish.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/3 10:01
 */
@Data
public class Chat {
    private Integer id;
    private Integer userFrom;
    private Integer userTo;
    private String content;
    private Integer contentStatus;
    private Date updateTime;
    private Date createTime;
}
