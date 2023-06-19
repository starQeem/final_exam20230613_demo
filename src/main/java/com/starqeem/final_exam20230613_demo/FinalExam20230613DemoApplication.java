package com.starqeem.final_exam20230613_demo;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableRabbit //开启基于注解的rabbitmq模式
@SpringBootApplication
@EnableAsync //开启异步
@EnableCaching //开启缓存
public class FinalExam20230613DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalExam20230613DemoApplication.class, args);
    }

}
