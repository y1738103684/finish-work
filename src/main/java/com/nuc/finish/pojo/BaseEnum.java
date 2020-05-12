package com.nuc.finish.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/11 10:26
 */
@Data
public class BaseEnum {
    private Integer id;
    private String enumType;
    private String enumTypeName;
    private String enumCode;
    private String enumDesc;
    private Date updateTime;
    private Date createTime;
}
