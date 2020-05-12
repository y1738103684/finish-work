package com.nuc.finish.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/20 15:41
 */
@Data
public class Region {
    private Integer id;
    private Integer parentId;
    private String regionName;
    private String regionType;
    private Date updateTime;
    private Date createTime;
}
