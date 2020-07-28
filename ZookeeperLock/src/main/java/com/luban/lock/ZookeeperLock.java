package com.luban.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 使用zookeeper实现锁
 */
public class ZookeeperLock implements Lock {

    private static final String ZK_IP_PORT = "192.168.50.62:2181";
    private static final String LOCK_NODE = "/LOCK";

    private CountDownLatch cdl = null;
    private ZkClient client = new ZkClient(ZK_IP_PORT);

    /**
     * 阻塞式加锁
     */
    @Override
    public void lock() {
        //1.尝试加锁
        if (tryLock()) {
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
                System.out.println("========节点被删除========");
                if (cdl != null) {
                    cdl.countDown();
                }
            }
        };
        //给节点加监听
        client.subscribeDataChanges(LOCK_NODE, iZkDataListener);
        if (client.exists(LOCK_NODE)) {
            cdl = new CountDownLatch(1);
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        client.unsubscribeDataChanges(LOCK_NODE, iZkDataListener);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /**
     * 非阻塞性加锁
     *
     * @return
     */
    @Override
    public boolean tryLock() {
        try {
            client.createPersistent(LOCK_NODE);
            System.out.println(Thread.currentThread().getName()+"------------加锁----------------"+LOCK_NODE);
            return true;
        } catch (ZkNodeExistsException e) {
            return false;
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        client.delete(LOCK_NODE);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
