package com.nuc.finish.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/3 10:06
 */
@Data
public class ChatVO {
    private Integer id;
    private Integer userFrom;
    private Integer userTo;
    private String content;
    private Integer status;
    private Date updateTime;
    private Date createTime;
}
