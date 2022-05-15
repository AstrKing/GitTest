package com.example.demo.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lisw
 * @create 2021/6/26 16:44
 */
@Component
@Slf4j
public class WarningConsumer {
    @RabbitListener(queues = {"WARNING_QUEUE"})
    public void receiveWarningMsg(Message message){
        log.warn("出现不可路由消息:",message);
    }
}
