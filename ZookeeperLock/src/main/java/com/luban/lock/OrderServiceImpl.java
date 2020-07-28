package com.luban.lock;

import java.util.concurrent.CountDownLatch;

public class OrderServiceImpl implements Runnable {
    private OrderCodeGenerator orderCodeGenerator=new OrderCodeGenerator();
    //同时并发的线程数
    private static final int NUM=100;
    //倒记数器
    private static CountDownLatch cdl=new CountDownLatch(NUM);
    public void run() {
        try {
            //等待线程初始化
            cdl.wait();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        getOrderCode();
    }
    private void getOrderCode() {
        String orderCode=orderCodeGenerator.getOrderCode();
        System.out.println(Thread.currentThread().getName()+"code:"+orderCode);
    }
    public static void main(String[] args) {
        for (int i = 0; i < NUM; i++) {
            new Thread(new OrderServiceImpl()).start();
            cdl.countDown();
        }
    }
}
