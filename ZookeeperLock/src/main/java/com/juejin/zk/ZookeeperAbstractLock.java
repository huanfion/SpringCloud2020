package com.juejin.zk;

import org.I0Itec.zkclient.ZkClient;

/**
 * @Auther: ly
 * @Date: 2019/4/25 11:41
 */
//将重复代码写入子类中
public abstract class ZookeeperAbstractLock extends AbstracLock {
    //zk连接地址
    private static final String CONNECTSTRING = "192.168.50.62:2181";
    //创建zk连接
    protected ZkClient zkClient = new ZkClient(CONNECTSTRING);

    protected static final String PATH = "/lock";

    protected static final String PATH2 = "/lock2";



//    public static void main(String[] args) {
//        List list = new ArrayList();
//        list.add("0000000002");
//        list.add("0000000003");
//        list.add("0000000004");
//        list.add("0000000005");
//        list.add("0000000006");
//        String lock = "/lock2/0000000004";
//        String substring = lock.substring(7);
//        int i = Collections.binarySearch(list, substring);
//        System.out.println(i);
//    }
}
