package com.nuc.finish.response;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/3 11:39
 */
@AllArgsConstructor
public enum CommonResponseEnum implements CommonReturn{
    UNKNOWN(00001, "未知错误"),
    USER_LOGIN_NOT(10001, "用户未登录"),
    USER_NOT_EXIST(10002, "用户不存在"),


    FILE_UPLOAD_FAIL(20001, "文件上传失败"),

    COMMENT_INSERT_FAIL(30001, "评论失败"),

    IDENTIFY_CODE_FAIL(40001, "验证码错误"),
    IDENTIFY_CODE_EXIST(40002, "验证码已存在");
    ;


    private Integer errCode;
    private String errMsg;

    @Override
    public Integer getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonReturn setErrMsg(String msg) {
        this.errMsg = msg;
        return this;
    }
}
