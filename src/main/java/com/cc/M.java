package com.cc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cc.generator.mapper")
public class M {
    public static void main(String[] args) {
        SpringApplication.run(M.class, args);
    }
}
