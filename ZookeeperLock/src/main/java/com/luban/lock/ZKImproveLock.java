package com.luban.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

/**
 * zookeeper lock提升性能
 */
public class ZKImproveLock implements Lock {
    private static final String ZK_IP_PORT = "192.168.50.62:2181";
    private ZkClient client = new ZkClient(ZK_IP_PORT);
    private static final String LOCK_PATH = "/LOCK";

    private CountDownLatch cdl=null;
    private String currentPath;//当前请求的节点
    private String beforePath;//当前请求的节点前一个节点

    public ZKImproveLock(){
        //判断有没有LOCK目录，没有则创建
        if(!this.client.exists(LOCK_PATH)){
            this.client.createPersistent(LOCK_PATH);
        }
    }
    @Override
    public void lock() {
        //1.尝试加锁
        if (tryLock()) {
            System.out.println(Thread.currentThread().getName()+"获得分布式锁！");
            return;
        }
        waitForLock();
        lock();
    }

    private void waitForLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (cdl != null) {
                    System.out.println(Thread.currentThread().getName()+"释放分布式锁！");
                    cdl.countDown();
                }
            }
        };

        //监听前一个节点的变化
        System.out.println(Thread.currentThread().getName()+"监听节点"+beforePath);
        client.subscribeDataChanges(beforePath,iZkDataListener);
        if(client.exists(beforePath)){
            cdl=new CountDownLatch(1);
            try {
                System.out.println(Thread.currentThread().getName()+"等待获取锁");
                cdl.await();
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }
        client.unsubscribeDataChanges(beforePath,iZkDataListener);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        //如果currentpath为空则为第一次尝试加锁
        if(currentPath==null||currentPath.length()<=0){
            currentPath=this.client.createEphemeralSequential(LOCK_PATH+"/","lock");
            System.out.println(Thread.currentThread().getName()+"------------加锁----------------"+currentPath);
        }
        //获取所有的临时节点并排序
        List<String> childrens = this.client.getChildren(LOCK_PATH);
        Collections.sort(childrens);
        System.out.println("第一个节点"+childrens.get(0));
        //如果当前节点就是第一个节点，返回true，加锁成功
        if(currentPath.equals(LOCK_PATH+"/"+childrens.get(0))){
            System.out.println("第一个节点"+currentPath);
            return true;
        }else{
            //如果不是第一个节点，则取它前面的节点，赋值给beforepath
            int index=Collections.binarySearch(childrens,currentPath.substring(LOCK_PATH.length()+1));
            beforePath=LOCK_PATH+"/"+childrens.get(index-1);
            System.out.println("------------前一个节点----------------"+beforePath);
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        System.out.println("------------释放锁----------------"+currentPath);
        client.delete(currentPath);
        client.close();
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
