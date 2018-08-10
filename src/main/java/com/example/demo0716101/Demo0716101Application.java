package com.example.demo0716101;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.example.demo0716101.Dao")
public class Demo0716101Application extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(Demo0716101Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Demo0716101Application.class, args);
    }
}
