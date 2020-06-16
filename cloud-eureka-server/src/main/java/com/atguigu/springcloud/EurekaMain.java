package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

class MyData{
    int number=0;
    public void addto60(){
        this.number=60;
    }
}
/**
 * @author huanfion
 * @version 1.0
 * @date 2020/3/13 11:41
 */
//@SpringBootApplication
//@EnableEurekaServer
public class EurekaMain {
    public static void main(String[] args) {
        //SpringApplication.run(EurekaMain.class,args);
        MyData data=new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+data.number);
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"\t"+data.number);
        },"AAA");

        while (data.number==0){
            //main线程一直等待，直到值不等于0
        }
        System.out.println(Thread.currentThread().getName()+"\t任务完成");

    }
}
