package com.serendipity.myold.generics.base01;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/12 1:13
 * FileName: Pair02
 * Description: com.generics.base01
 */
public class Pair02<T> {

    private T first;
    private T last;

    //这样一来，创建new Pair<String>()和创建new Pair<Integer>()就全部成了Object，显然编译器要阻止这种类型不对的代码。
    //
    //要实例化T类型，我们必须借助额外的Class<T>参数：
    public Pair02() {
        // Compile error:
//        first = new T();
//        last = new T();
    }

    public Pair02(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        first = clazz.newInstance();
        last = clazz.newInstance();
    }

}
