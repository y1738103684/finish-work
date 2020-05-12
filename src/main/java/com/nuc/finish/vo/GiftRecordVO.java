package com.nuc.finish.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/27 18:26
 */
@Data
public class GiftRecordVO {
    private Integer id;
    private Integer giftId;
    private Integer userId;
    private Integer number;
    private Date updateTime;
    private Date createTime;
}
