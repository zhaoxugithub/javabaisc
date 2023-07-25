package com.serendipity.myold.log;

import java.util.logging.Logger;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/7 14:04
 * FileName: LogDemo01
 * Description: com.log
 */
/*
这个是java默认自带的日志系统，不常用

日志级别：由高到低分别为：
SEVERE
WARNING
INFO
CONFIG
FINE
FINER
FINEST


默认是info级别，所以只有info及以上级别的日志可以打印出来

 */
public class LogDemo01 {
    public static void main(String[] args) {

        Logger logger = Logger.getGlobal();
        logger.info("start process...");
        logger.warning("memory is running out ...");
        logger.fine("ignored..");
        logger.severe("process will  be terminated....");
    }
}
