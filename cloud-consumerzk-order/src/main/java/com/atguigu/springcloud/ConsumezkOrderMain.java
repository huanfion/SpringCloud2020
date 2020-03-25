package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/17 9:32
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumezkOrderMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsumezkOrderMain.class,args);
    }
}
