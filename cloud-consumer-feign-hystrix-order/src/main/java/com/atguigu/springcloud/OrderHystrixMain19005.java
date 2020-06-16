package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/30 21:13
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OrderHystrixMain19005 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain19005.class, args);
    }
}
