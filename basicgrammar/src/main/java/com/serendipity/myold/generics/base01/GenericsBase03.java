package com.serendipity.myold.generics.base01;

import org.junit.jupiter.api.Test;

/**
 * 泛型接口
 */
@SuppressWarnings("all")
public class GenericsBase03 {
    interface Info<T> {
        public T getVar();
    }

    class InfoImpl<T> implements Info<T> {

        private T var;

        public InfoImpl(T var) {
            this.setVar(var);
        }

        public void setVar(T var) {
            this.var = var;
        }

        @Override
        public T getVar() {
            return this.var;
        }
    }

    @Test
    public void test() {
        Info<String> i = null;        // 声明接口对象
        i = new InfoImpl<String>("汤姆");  // 通过子类实例化对象
        System.out.println("内容：" + i.getVar());
    }
}
