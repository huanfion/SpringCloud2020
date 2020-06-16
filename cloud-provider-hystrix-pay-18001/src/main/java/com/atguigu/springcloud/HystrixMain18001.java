package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/27 12:13
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class HystrixMain18001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixMain18001.class, args);
    }
}
