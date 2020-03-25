package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/12 12:24
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class PayApplication18006 {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication18006.class, args);
    }
}
