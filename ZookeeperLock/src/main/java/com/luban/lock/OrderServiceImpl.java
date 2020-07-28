package com.luban.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderServiceImpl implements Runnable {
    private OrderCodeGenerator orderCodeGenerator = new OrderCodeGenerator();
    //同时并发的线程数
    private static final int NUM = 10;
    //倒记数器
    private static CountDownLatch cdl = new CountDownLatch(NUM);
//    private static Lock lock = new ReentrantLock();
    private static ZKImproveLock lock=new ZKImproveLock();
    @Override
    public void run() {
        try {
            //等待线程初始化
            cdl.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        getOrderCode();
    }

    private void getOrderCode() {
        lock.lock();
        try {
            String orderCode = orderCodeGenerator.getOrderCode();
            System.out.println(Thread.currentThread().getName() + "code:" + orderCode);
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < NUM; i++) {
            new Thread(new OrderServiceImpl()).start();
            cdl.countDown();
        }
    }
}
