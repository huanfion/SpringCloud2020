package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/26 18:43
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients//开启feign
public class FeignOrderMain19004 {
    public static void main(String[] args) {
        SpringApplication.run(FeignOrderMain19004.class,args);
    }
}
