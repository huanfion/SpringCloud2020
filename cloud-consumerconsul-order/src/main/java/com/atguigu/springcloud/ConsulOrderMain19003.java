package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/25 9:29
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulOrderMain19003 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulOrderMain19003.class, args);
    }
}
