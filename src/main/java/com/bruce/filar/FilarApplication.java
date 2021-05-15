package com.bruce.filar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bruce.filar.dao")
public class FilarApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilarApplication.class, args);
    }

}
