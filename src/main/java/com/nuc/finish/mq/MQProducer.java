package com.nuc.finish.mq;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.CharSet;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/14 15:16
 */
@Component
public class MQProducer {
    private DefaultMQProducer producer;


    @Value("${mq.servername.addr}")
    private String nameServer;

    @Value("${mq.topicname}")
    private String topicName;


    @PostConstruct
    private void init() throws MQClientException {
        producer = new DefaultMQProducer("sms_producer");
        producer.setNamesrvAddr(nameServer);
        producer.start();
    }

    public Boolean asyncSms(String tel, String msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Map<String, String> map = new HashMap<>();
        map.put("tel", tel);
        map.put("msg", msg);
        Message message = new Message(topicName, "send", JSON.toJSON(map).toString().getBytes(Charset.forName("UTF-8")));
        producer.send(message);
        return true;
    }
}
