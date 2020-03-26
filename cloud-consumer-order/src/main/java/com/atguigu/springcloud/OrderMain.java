package com.atguigu.springcloud;

import com.atguige.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/12 21:59
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name ="CLOUD-PAY-SERVICE",configuration = MySelfRule.class)
public class OrderMain {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain.class,args);
    }
}
