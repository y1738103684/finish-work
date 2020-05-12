package com.nuc.finish.response;

import lombok.Data;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/3 11:24
 */
@Data
public class Response {
    private String status;
    private Object data;

    public static Response create(Object data) {
        return create("success", data);
    }

    public static Response create(String status, Object data) {
        Response response = new Response();
        response.setData(data);
        response.setStatus(status);
        return response;
    }
}
