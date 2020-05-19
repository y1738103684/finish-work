package com.nuc.finish.service;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/15 21:08
 */
public interface MailService {
    void simpleSendMail(String to, String title, String content);
}
