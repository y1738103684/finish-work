package com.nuc.finish.response;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/3 11:25
 */
public interface CommonReturn {
    Integer getErrCode();
    String getErrMsg();
    CommonReturn setErrMsg(String msg);
}
