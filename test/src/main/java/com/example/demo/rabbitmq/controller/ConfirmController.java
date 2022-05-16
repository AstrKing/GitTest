package com.example.demo.rabbitmq.controller;

import com.example.demo.rabbitmq.config.MyCallBack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lisw
 * @create 2021/6/26 15:38
 */
@Slf4j
@RestController
@RequestMapping("/confirm")
public class ConfirmController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendConfirm/{msg}")
    public void sendConfirmMessage(@PathVariable("msg")String msg){
        /**声明回调的形参*/
//        MyCallBack myCallBack = new MyCallBack();
        CorrelationData correlationData = new CorrelationData("1");
        rabbitTemplate.convertAndSend("confirm_exchange", "confirm_key", msg,correlationData);
        /*log.info("【id】为"+myCallBack.getId());
        if(myCallBack.getId()!=""&&myCallBack.getId()!=null&&myCallBack.getId().equals("1")){
            log.info("消息端知道失败");
        }*/
        log.info("发送信息为:" + msg);
    }
}
