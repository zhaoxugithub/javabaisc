package com.serendipity.myold.base;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/20 0:07
 * FileName: Base64Demo
 * Description: com.java8.base
 * <p>
 * Base64增强
 */
public class Base64Demo {



    public static void main(String[] args) {
        final String text = "Lets learn java8!";
        // 将text按照base64 UTF-8 进行编码
        final String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);

        // 按照base64 UTF-8 进行解码
        String s = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
        System.out.println(s);
    }
}
