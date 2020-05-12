package com.nuc.finish.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/3 21:26
 */
@Data
public class Comment {
    private Integer id;
    private Integer videoId;
    private String comment;
    private User user;
    private User replayUser;
    private Integer parentId;
    private Integer commentStatus;
    private Date updateTime;
    private Date createTime;

    private List<Comment> childComments;
}
