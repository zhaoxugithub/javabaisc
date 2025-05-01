package com.serendipity.myold.exception.exce;

import java.util.Optional;

public class NullPointerExceptionDemo02 {

    public static void main(String[] args) {
        Optional<String> dfdsf = test("ok");
        // 通过isPresent 方法返回判断是否是null
        System.out.println(dfdsf.isPresent());
        if (dfdsf.isPresent()) {
            //todo
            // 如果有值要怎么操作
        }else {
            // 如果没有值要怎么操作
        }

    }

    public static Optional<String> test(String string) {
        if (string.equals("ok")) {
            return Optional.empty();
        }
        return Optional.ofNullable("error");
    }
}
