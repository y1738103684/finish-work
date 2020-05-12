package com.nuc.finish.exception;

import com.nuc.finish.response.CommonReturn;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/3 11:24
 */
public class CommonException extends Exception implements CommonReturn {
    CommonReturn commonReturn;

    public CommonException (CommonReturn commonReturn) {
        super();
        this.commonReturn = commonReturn;
    }

    public CommonException (CommonReturn commonReturn, String msg) {
        super();
        this.commonReturn = commonReturn;
        this.commonReturn.setErrMsg(msg);
    }

    @Override
    public Integer getErrCode() {
        return commonReturn.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return commonReturn.getErrMsg();
    }

    @Override
    public CommonReturn setErrMsg(String msg) {
        return commonReturn.setErrMsg(msg);
    }
}
