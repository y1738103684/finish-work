package com.nuc.finish.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {


    @Autowired
    private MailServiceImpl service;

    @Test
    public void sendMail() {
        String to = "1522017075@qq.com";
        String content = "测试邮件";
        String title = "测试标题";
        service.simpleSendMail(to, title, content);
    }
}