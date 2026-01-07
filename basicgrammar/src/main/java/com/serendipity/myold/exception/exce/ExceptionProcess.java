package com.serendipity.myold.exception.exce;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName ExceptionProcess
 * Description 异常处理
 * Author 11931
 * Date 2022-10-22:18:33
 * Version 1.0
 **/
@SuppressWarnings("all")
@Slf4j
public class ExceptionProcess {
    private static class User {
    }

    /**
     * Java 异常本质 --抛出异常
     * <p>
     * 如果遇到一个步骤或者问题，导致后续代码无法继续执行下去的时候
     * 需要捕获异常或者抛出异常
     */
    private void throwException() {
        User user = null;
        // 如果user为空就抛出异常
        if (user == null) {
            throw new NullPointerException();
        }
        System.out.println("继续执行");
    }

    /**
     * 不能捕获空指针异常
     */
    private void canNotCatchNpeException() {
        try {
            throwException();
        } catch (ClassCastException cce) {
            System.out.println(cce.getMessage());
            System.out.println(cce.getClass().getName());
        }
    }

    /**
     * 能捕获空指针异常
     */
    private void canCatchNpeException() {
        try {
            throwException();
        } catch (ClassCastException cce) {
            System.out.println(cce.getMessage());
            System.out.println(cce.getClass().getName());
        } catch (NullPointerException npe) {
            log.warn("excepton message = {},className = {}", npe.getMessage(), npe.getClass().getName());
        }
        System.out.println("成功捕获到异常");
    }

    public static void main(String[] args) {
        ExceptionProcess process = new ExceptionProcess();
        // 异常类型不一样，捕获不到
        // process.canNotCatchNpeException();
        process.canCatchNpeException();
        // process.throwException();
    }
}
