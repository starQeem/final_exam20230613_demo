package com.starqeem.final_exam20230613_demo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import static com.starqeem.final_exam20230613_demo.constant.constant.*;

/**
 * @Date: 2023/6/14 19:42
 * @author: Qeem
 */
@Service
public class sendEmailMessage {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private emailService emailService;
    /*
    * 发送邮件
    * */
    @Async //异步执行
    public void emailInput1(String title,String email,String content) throws MessagingException {
        emailService.sendVerificationCode(title,email,content);
        //发送消息
        rabbitTemplate.convertAndSend("email","已将邮件发送至邮箱: " + email );
    }
    /*
    * 发送通知邮件
    * */
    @Async  //异步执行
    public void emailInput2(String email) throws MessagingException {
        emailService.sendVerificationCode("通知:",EMAIL_ADDRESS,"邮件已发送至邮箱:" + email);
        //发送消息
        rabbitTemplate.convertAndSend("email","已发送通知邮箱");
    }
}
