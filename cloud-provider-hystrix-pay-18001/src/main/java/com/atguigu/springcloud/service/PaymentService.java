package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/27 12:20
 */
@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id){
        return "线程"+Thread.currentThread().getName()+"paymentInfo_OK 正常运行,id"+id;
    }
    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandle",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "6000")})
    public String paymentInfo_TimeOut(Integer id){
//        int age=10/0;
        int timenumber=5;
        try {
            TimeUnit.SECONDS.sleep(timenumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程"+Thread.currentThread().getName()+"paymentInfo_TimeOut,id"+id+"耗时（秒）"+timenumber;
    }

    public String paymentInfo_timeoutHandle(Integer id){
        return  "线程"+Thread.currentThread().getName()+"调用服务降级方法";
    }
}
