package com.nuc.finish.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/3 10:05
 */
@Data
public class CommentVO{
    private Integer id;
    private Integer videoId;
    private String comment;
    private Integer userId;
    private Integer replayUserId;
    private Integer parentId;
    private Integer commentStatus;
    private Date updateTime;
    private Date createTime;
}
