package com.example.demo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author lisw
 * @create 2021/6/26 15:29
 * 发布确认兜底方案  添加缓存测试
 */
@Configuration
public class ConfirmConfig {
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";
    public static final String CONFIRM_QUEUE_NAME = "confirm_queue";
    private static final String CONFIRM_ROUTING_KEY = "confirm_key";
  /*  public static final String BACKUP_EXCHANGE_NAME = "BACKUP_EXCHANGE";
    public static final String BACKUP_QUEUE_NAME = "BACKUP_QUEUE";
    public static final String WARNING_QUEUE_NAME = "WARNING_QUEUE";*/

    //声明业务 Exchange
  /*  @Bean("backUpExchange")
    public FanoutExchange backUpExchange() {
        return new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }*/

    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        return new DirectExchange(CONFIRM_EXCHANGE_NAME);
//        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME).durable(true).withArgument("alternate-exchange", BACKUP_EXCHANGE_NAME).build();
    }

    /*// 声明确认队列
    @Bean("backUpQueue")
    public Queue backUpQueue() {
        return QueueBuilder.durable(BACKUP_QUEUE_NAME).build();
    }

    @Bean("warningQueue")
    public Queue warningQueue() {
        return QueueBuilder.durable(WARNING_QUEUE_NAME).build();
    }*/

    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    // 声明确认队列绑定关系
    @Bean
    public Binding queueBinding(@Qualifier("confirmQueue") Queue queue,
                                @Qualifier("confirmExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ConfirmConfig.CONFIRM_ROUTING_KEY);
    }

   /* @Bean
    public Binding backupBindingExchange(@Qualifier("backUpQueue") Queue queue,
                                         @Qualifier("backUpExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding warningBindingExchange(@Qualifier("warningQueue") Queue queue,
                                          @Qualifier("backUpExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }*/
}
