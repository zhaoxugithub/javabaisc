package com.serendipity.myold.generics.base01;

import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class GenericsBase07 {


    class GenericType<T> {

        private T data;

        T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }


    /*
    // 返回确切的泛型参数, 如Map<String, Integer>返回[String, Integer]
    Type[] getActualTypeArguments();
    //返回当前class或interface声明的类型, 如List<?>返回List
    Type getRawType();
    //返回所属类型. 如,当前类型为O<T>.I<S>, 则返回O<T>. 顶级类型将返回null
    Type getOwnerType();
     */
    @Test
    public void test() {
        // 生成GenericType 子类对象
        GenericType<String> genericType = new GenericType<String>() {
        };
        Type genericSuperclass = genericType.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        System.out.println(Arrays.toString(actualTypeArguments));
    }

}
