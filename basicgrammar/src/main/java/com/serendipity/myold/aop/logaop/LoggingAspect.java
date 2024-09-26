package com.serendipity.myold.aop.logaop;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    private static final String TAG = "LoggingAspect";

    @Before("@annotataion()")
    public void logMethodEntry(){

    }

}
