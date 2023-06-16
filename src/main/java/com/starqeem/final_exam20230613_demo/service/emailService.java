package com.starqeem.final_exam20230613_demo.service;

/**
 * @Date: 2023/6/14 10:55
 * @author: Qeem
 */

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import static com.starqeem.final_exam20230613_demo.constant.constant.EMAIL_ADDRESS;

/**
 * @Date: 2023/5/7 0:32
 * @author: Qeem
 */
@Component
public class emailService {
    @Resource
    private JavaMailSender javaMailSender;
    public void sendVerificationCode(String title,String email, String content) throws MessagingException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(EMAIL_ADDRESS);   //发送邮件的邮箱号
        simpleMailMessage.setTo(email);   //接收邮件的邮箱号
        simpleMailMessage.setText(content);   //邮件内容
        simpleMailMessage.setSubject(title);   //邮件标题
        javaMailSender.send(simpleMailMessage);  //发送
    }
}