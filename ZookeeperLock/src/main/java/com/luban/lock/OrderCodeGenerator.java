package com.luban.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderCodeGenerator {
    //自增长序列
    private static int i=0;
    //按照年-月-日-时-分-秒-自增长序列 规格生成订单号
    public String getOrderCode(){
        Date now=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
        return sdf.format(now)+ ++i;
    }
}
