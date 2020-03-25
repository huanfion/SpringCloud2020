package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/12 21:50
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    //@LoadBalanced注解提供了RestTemplate负载均衡的能力
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
