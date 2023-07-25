package com.serendipity.learn.c_026;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/11 11:42
 * FileName: MySync
 * Description: com.java.thread.c_026
 * <p>
 * 自定义同步器
 */
public class MySync extends AbstractQueuedSynchronizer {

    /**
     * 尝试获取锁
     *
     * @param arg
     * @return
     */
    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    /**
     * 尝试去释放锁
     *
     * @param arg
     * @return
     */
    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    /**
     * 判断是否是锁定状态
     *
     * @return
     */
    @Override
    protected boolean isHeldExclusively() {
        return getState() == 1;
    }
}
