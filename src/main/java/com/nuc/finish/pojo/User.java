package com.nuc.finish.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/2 12:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private Integer type;
    private String username;
    private String password;
    private Integer country;
    private Integer province;
    private String countryName;
    private String cityName;
    private String provinceName;
    private Integer city;
    private Long tel;
    private String email;
    private String name;
    private Boolean isVip;
    private Integer vipPoints;
    private Date createTime;
    private Date updateTime;
    private String photo;
}
