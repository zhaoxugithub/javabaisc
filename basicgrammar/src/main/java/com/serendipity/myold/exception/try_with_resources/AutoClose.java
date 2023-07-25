package com.serendipity.myold.exception.try_with_resources;

/**
 * ClassName AutoClose
 * Description TODO
 * Author 11931
 * Date 2022-11-03:1:00
 * Version 1.0
 **/
public class AutoClose implements AutoCloseable {
    @Override
    public void close() {
        System.out.println(">>> close");
        throw new RuntimeException("Exception in close()");
    }

    public void work() throws MyException {
        System.out.println(">>> work");
        throw new RuntimeException("Exception in work()");
    }
}
