package com.serendipity.sysio.mytestreactor;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/5/30 18:45
 * FileName: MainThread
 * Description: io.mytestreactor
 */

public class MainThread {


    public static void main(String[] args) {
        //这里不做关于IO 和业务的事情
        //1.创建IOThread (1个或者多个)
        SelectorThreadGroup boss = new SelectorThreadGroup(3);
        //混杂模式，只有一个线程负责accept,每个都会被分配client,进行R/W
//        SelectorThreadGroup stg2 = new SelectorThreadGroup(3);
        //2.我应该把监听的server注册到某一个selector上

        SelectorThreadGroup worker = new SelectorThreadGroup(3);

        boss.setWorker(worker);
        boss.bind(9991);
        boss.bind(9992);

    }
}
