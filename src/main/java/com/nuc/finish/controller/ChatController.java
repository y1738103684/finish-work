package com.nuc.finish.controller;

import com.nuc.finish.exception.CommonException;
import com.nuc.finish.pojo.Chat;
import com.nuc.finish.pojo.User;
import com.nuc.finish.response.CommonResponseEnum;
import com.nuc.finish.response.Response;
import com.nuc.finish.service.ChatService;
import com.nuc.finish.vo.ChatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/3 10:23
 */
@RestController
@RequestMapping("/chat")
public class ChatController extends BaseController{

    @Autowired
    private ChatService chatService;

    @RequestMapping("/insert")
    public Response insertChat(@RequestBody ChatVO vo) {
        int i = chatService.addChat(vo);
        return Response.create(i);
    }

    @RequestMapping("/selectByToAndFrom")
    public Response selectChat(@RequestBody ChatVO vo) {
        Chat chat = chatService.queryByToAndForm(vo);
        return Response.create(chat);
    }

    @RequestMapping("/chatBasicData")
    public Response getChatBasicData(@RequestBody ChatVO vo) throws CommonException {
        User loginUser = getLoginUser();
        if (loginUser == null) {
            throw new CommonException(CommonResponseEnum.USER_LOGIN_NOT);
        }
        List<Chat> chats = chatService.chatBasicData(vo);
        return Response.create(chats);
    }
}
