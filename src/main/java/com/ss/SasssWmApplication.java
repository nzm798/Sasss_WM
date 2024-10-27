package com.ss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ServletComponentScan
@ComponentScan
@EnableAutoConfiguration
@Controller
public class SasssWmApplication {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String WelcomePage() {
        logger.info("Network redirects:{}", "/managerlogin");
        return "redirect:/managerlogin";
    }

    public static void main(String[] args) {
        SpringApplication.run(SasssWmApplication.class, args);
    }

}
