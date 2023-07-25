package com.serendipity.myold.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/7 14:11
 * FileName: LogDemo02
 * Description: com.log
 */
/*
使用第三方日志系统 commons log

日志级别总共定义了6个：
FATAL
ERROR
WARNING
INFO
DEBUG
TRACE

需要结合配置文件使用
 */
public class LogDemo02 {

    public static void main(String[] args) {

        Log log = LogFactory.getLog(LogDemo02.class);
        log.info("start ....");
        log.warn("end..");

    }
}
