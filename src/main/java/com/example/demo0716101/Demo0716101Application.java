package com.example.demo0716101;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo0716101.Dao")
public class Demo0716101Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo0716101Application.class, args);
    }
}
