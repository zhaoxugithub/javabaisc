package com.serendipity.myold.generics.base01;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/12 1:17
 * FileName: Pair02_Main
 * Description: com.generics.base01
 */
public class Pair02_Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Pair02<String> stringPair02 = new Pair02<>();
        //下面这种方式给
        //因为传入了Class<String>的实例，所以我们借助String.class就可以实例化String类型
        Pair02<String> stringPair021 = new Pair02<>(String.class);
    }
}
