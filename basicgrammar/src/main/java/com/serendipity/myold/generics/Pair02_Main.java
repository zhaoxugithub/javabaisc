package com.serendipity.myold.generics;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/12 1:17
 * FileName: Pair02_Main
 * Description: com.generics.base01
 */
@Slf4j
@SuppressWarnings("all")
public class Pair02_Main {

    class Pair02<T> {
        private T first;
        private T last;

        // 这样一来，创建new Pair<String>()和创建new Pair<Integer>()就全部成了Object，显然编译器要阻止这种类型不对的代码。
        // 要实例化T类型，我们必须借助额外的Class<T>参数：
        public Pair02() {
            // Compile error:
            // first = new T();
            // last = new T();
        }

        public Pair02(Class<T> clazz) throws InstantiationException, IllegalAccessException {
            first = clazz.newInstance();
            last = clazz.newInstance();
        }
    }

    @Test
    public void test() throws Exception {
        Pair02<String> stringPair02 = new Pair02<>();
        // 下面这种方式给
        // 因为传入了Class<String>的实例，所以我们借助String.class就可以实例化String类型
        Pair02<String> stringPair021 = new Pair02<>(String.class);
    }
}
