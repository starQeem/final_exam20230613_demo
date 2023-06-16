package com.starqeem.final_exam20230613_demo.service;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @Date: 2023/6/14 12:48
 * @author: Qeem
 * 消息接收
 */
@Component
public class RabbitMQService {
    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("email"))
    public void receiveMessage(String message) {
        System.out.println("message = " + message);
    }
}
