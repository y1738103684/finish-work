package com.nuc.finish.service;

import com.nuc.finish.pojo.Chat;
import com.nuc.finish.vo.ChatVO;

import java.util.Date;
import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/3 10:18
 */
public interface ChatService {

    int addChat(ChatVO vo);

    Chat queryByToAndForm(ChatVO vo);

    List<Chat> chatBasicData(ChatVO vo);
}
