package com.feiyue.cucumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.feiyue.cucumber.mapper")
@ComponentScan(basePackages = {"com.feiyue.**.**"})
public class CucumberApplication {

    public static void main(String[] args) {
        SpringApplication.run(CucumberApplication.class, args);
    }

}
