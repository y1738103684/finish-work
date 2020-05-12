package com.nuc.finish.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuc.finish.exception.CommonException;
import com.nuc.finish.pojo.User;
import com.nuc.finish.response.CommonResponseEnum;
import com.nuc.finish.response.CommonReturn;
import com.nuc.finish.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/3 11:59
 */
public class BaseController {
    public static final String TOKEN = "token";

    @Autowired
    private RedisTemplate redisTemplate;
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public Object handlerException(Exception exception) {
//        if (exception instanceof CommonReturn) {
//            CommonException commonException = (CommonException) exception;
//            Map<String, Object> map = new HashMap<>();
//            map.put("errCode", commonException.getErrCode());
//            map.put("errMsg", commonException.getErrMsg());
//            return Response.create("fail", map);
//        } else {
//            CommonException commonException = new CommonException(CommonResponseEnum.UNKNOWN);
//            Map<String, Object> map = new HashMap<>();
//            map.put("errCode", commonException.getErrCode());
//            map.put("errMsg", commonException.getErrMsg());
//            return Response.create("fail", map);
//        }
//
//    }

    public User getLoginUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Token");
        if (token == null) {
            return null;
        }
        JSONObject user = (JSONObject) redisTemplate.opsForValue().get(token);
        User res = JSONObject.toJavaObject(user, User.class);
        if (res != null) {
            redisTemplate.opsForValue().set(token, res, 30, TimeUnit.MINUTES);
        }
        return res;
    }

    public String getToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("Token");
    }

}
