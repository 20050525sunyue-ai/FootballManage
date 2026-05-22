package com.sky.content;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.sky")
@MapperScan("com.sky.content.mapper")
@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
@EnableScheduling //开启任务调度
public class ContentApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApiApplication.class, args);
        log.info("content-api started on port 8081, context-path /api");
    }
}
