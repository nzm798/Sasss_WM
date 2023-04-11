package com.ss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ServletComponentScan
@ComponentScan
@RestController
@EnableAutoConfiguration
public class SasssWmApplication {
    @RequestMapping("/")
    public String SayHello(){
        return "Hello World";
    }
    public static void main(String[] args) {
        SpringApplication.run(SasssWmApplication.class, args);
    }

}
