package com.juejin.zk;

import com.luban.lock.OrderCodeGenerator;

/**
 * @Auther: ly
 * @Date: 2019/4/25 14:39
 */

public class OrderService implements Runnable {

    private OrderCodeGenerator orderNumGenerator = new OrderCodeGenerator();

    private Lock lock = new ZookeeperDistrbuteLock2();



    @Override
    public void run() {
        getNum();
    }

    public void getNum() {
        try {
            lock.getLock();
            String number = orderNumGenerator.getOrderCode();
            System.out.println(Thread.currentThread().getName() + ",生产订单ID:" + number);
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            lock.unLock();
        }
    }



    public static void main(String[] args) {
        System.out.println("##生产唯一订单号##");
        for (int i = 0; i < 10 ; i++) {
            new Thread(new OrderService()).start();
        }
    }
}
