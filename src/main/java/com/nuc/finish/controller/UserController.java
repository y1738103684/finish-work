package com.nuc.finish.controller;

import com.nuc.finish.exception.CommonException;
import com.nuc.finish.pojo.User;
import com.nuc.finish.response.CommonResponseEnum;
import com.nuc.finish.response.Response;
import com.nuc.finish.service.UserService;
import com.nuc.finish.vo.LoginVO;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/2 12:02
 */
@Controller
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/user/login")
    @ResponseBody
    public Response login(@RequestBody LoginVO vo) throws CommonException {

        User user = userService.login(vo);
        if (user == null) {
            throw new CommonException(CommonResponseEnum.USER_NOT_EXIST);
        }
        //单点登录
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(token, user, 30, TimeUnit.MINUTES);

        return Response.create(token);
    }

    @RequestMapping("/user/register")
    @ResponseBody
    public Response register(@RequestBody LoginVO vo) throws CommonException {
        String register = userService.register(vo);
        return Response.create(register);
    }

    @RequestMapping("/user/identify")
    @ResponseBody
    public Response identify(@RequestBody LoginVO vo) throws CommonException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        String register = userService.getIdentifyCode(vo);
        return Response.create(register);
    }

    @RequestMapping("/user/update")
    @ResponseBody
    public Response updateUser(@RequestBody LoginVO vo, HttpServletRequest request) throws CommonException {
        User loginUser = getLoginUser();
        if (loginUser == null) {
            throw new CommonException(CommonResponseEnum.USER_LOGIN_NOT);
        }
        String token = request.getHeader("Token");
        User user = userService.updateUser(vo, token);
        return Response.create(user);
    }

    @RequestMapping("/user/changePwd")
    @ResponseBody
    public Response changePwd(@RequestBody LoginVO vo) throws CommonException {
        String msg = userService.changePwd(vo);
        return Response.create(msg);
    }

    @RequestMapping("/user/sign-out")
    @ResponseBody
    public Response deleteToken(@RequestParam("token") String token) throws CommonException {
        redisTemplate.delete(token);
        return Response.create(true);
    }

    @RequestMapping("/user/token")
    @ResponseBody
    public Response identifyToken() {
        User loginUser = getLoginUser();
        return loginUser == null ? Response.create(false) : Response.create(loginUser);


    }

    @RequestMapping("/user/getVip")
    @ResponseBody
    public Response getVip(@RequestParam("userId") Integer userId, HttpServletRequest request) throws CommonException {
        String token = request.getHeader("Token");
        User loginUser = getLoginUser();
        if (loginUser == null) {
            throw new CommonException(CommonResponseEnum.USER_LOGIN_NOT);
        }
        int vip = userService.getVip(userId);
        if (vip > 0) {
            loginUser.setIsVip(true);
        }
        redisTemplate.opsForValue().set(token, loginUser);
        return Response.create(vip);
    }

}
