package com.nuc.finish.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/2 12:01
 */
@Data
public class LoginVO {
    private Integer id;
    private Integer type;
    private String username;
    private String password;
    private Integer country;
    private Integer province;
    private Integer city;
    private String tel;
    private String email;
    private String name;
    private Boolean isVip;
    private Integer vipPoints;
    private Date createTime;
    private Date updateTime;
    private String code;
}
