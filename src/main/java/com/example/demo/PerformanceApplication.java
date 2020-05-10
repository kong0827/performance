package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PerformanceApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PerformanceApplication.class);
    }

    public static void main(String[] args) {
        //String sn = com.blm.utils.OrderUtil.generateSn();
        //Console.log(sn.length());

        System.out.println("system start...");
        // 添加如下代码，主要是修改不用重新启动的意思
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(PerformanceApplication.class, args);
    }

}
