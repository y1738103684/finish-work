package com.nuc.finish.controller;

import com.github.pagehelper.PageInfo;
import com.nuc.finish.common.Pagination;
import com.nuc.finish.model.OrderModel;
import com.nuc.finish.pojo.User;
import com.nuc.finish.pojo.Video;
import com.nuc.finish.response.CommonResponseEnum;
import com.nuc.finish.response.Response;
import com.nuc.finish.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/11 15:49
 */
@RestController
public class OrderController extends BaseController{

    @Autowired
    private OrderService orderService;

    @RequestMapping("order/isExistOrder")
    public Response isExistOrder(@RequestParam("videoId") Integer videoId, @RequestParam("userId") Integer userId) {
        Boolean existOrder = orderService.isExistOrder(videoId, userId);
        return Response.create(existOrder);
    }

    @RequestMapping("order/buyVideo")
    public Response buyVideo(@RequestParam("videoId") Integer videoId, HttpServletRequest request) {
        User loginUser = getLoginUser();
        if (loginUser == null) {
            return Response.create(CommonResponseEnum.USER_LOGIN_NOT.getErrCode());
        }
        String token = request.getHeader("Token");
        Boolean bool = orderService.buyVideo(loginUser, videoId, token);
        return Response.create(bool);
    }

    @RequestMapping("order/getSelfVideo")
    public Response getSelfVideo(@RequestBody Pagination pagination) {
        User loginUser = getLoginUser();
        if (loginUser == null) {
            return Response.create(CommonResponseEnum.USER_LOGIN_NOT.getErrCode());
        }
        PageInfo<OrderModel> selfVideo = orderService.getSelfVideo(loginUser, pagination);
        return Response.create(selfVideo);
    }
}
