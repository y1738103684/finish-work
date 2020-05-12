package com.nuc.finish.service.impl;

import com.nuc.finish.dao.ChatMapper;
import com.nuc.finish.pojo.Chat;
import com.nuc.finish.service.ChatService;
import com.nuc.finish.util.DateUtil;
import com.nuc.finish.vo.ChatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/3 10:20
 */
@Service
public class ChatServiceImpl implements ChatService {
    public static final Integer READ_STATUS = 1;
    public static final Integer NUMBER_THREE = 3;


    @Autowired
    private ChatMapper chatMapper;

    @Override
    public int addChat(ChatVO vo) {
        return chatMapper.insertChat(vo);
    }

    @Override
    public Chat queryByToAndForm(ChatVO vo) {
        while (true) {
            Chat chat = chatMapper.selectByToAndFrom(vo);
            if (chat != null) {
                int i = chatMapper.updateStatus(chat.getId(), READ_STATUS);
                if (i > 0) {
                    return chat;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Chat> chatBasicData(ChatVO vo) {
        Date beforeDay = DateUtil.getBeforeDay(new Date(), NUMBER_THREE);
        List<Chat> chats = chatMapper.listByCreateTime(vo, beforeDay);
        return chats;
    }
}
