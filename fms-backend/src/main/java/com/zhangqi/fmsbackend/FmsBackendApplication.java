package com.zhangqi.fmsbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zhangqi.fmsbackend.mapper")
//@ComponentScan("com.zhangqi.fmsbackend")
@SpringBootApplication
public class FmsBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(FmsBackendApplication.class, args);
    }

}
