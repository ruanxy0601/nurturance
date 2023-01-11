package com.ruanxy.nurturance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@MapperScan("com.ruanxy.nurturance.mapper")
//@EnableWebMvc
@EnableOpenApi
public class NurturanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NurturanceApplication.class, args);
    }

}
