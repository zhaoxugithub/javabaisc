package com.serendipity.learn.c_030;

import java.util.concurrent.LinkedTransferQueue;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/2 22:04
 * FileName: TransferQueue_v1
 * Description: com.java.thread.c_030
 *
 * 多人对多人之间的手对手
 */
public class TransferQueue_v1 {

    public static void main(String[] args) throws InterruptedException {

        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //等待多个线程取走
        strs.transfer("aaa");
        //strs.put("aaa");
		/*new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/
    }
}
