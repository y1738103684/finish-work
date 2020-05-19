package com.nuc.finish.mq;

import com.alibaba.fastjson.JSON;
import com.nuc.finish.util.SmsUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/14 15:20
 */
@Component
public class MQConsumer {
    private DefaultMQPushConsumer consumer;

    @Value("${mq.servername.addr}")
    private String nameServer;

    @Value("${mq.topicname}")
    private String topicName;

    @PostConstruct
    public void init() throws MQClientException {
        consumer = new DefaultMQPushConsumer("smsConsumer");
        consumer.setNamesrvAddr(nameServer);
        //消费的topic
        consumer.subscribe(topicName, "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                MessageExt messageExt = list.get(0);
                Map map = (Map)JSON.parseObject(messageExt.getBody(), Map.class);
                String tel = (String)map.get("tel");
                String msg = (String)map.get("msg");
                Integer i  = SmsUtil.sendMsg(tel, msg);
                String message = SmsUtil.getMessage(i);
                System.out.println(message);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}
