package com.serendipity.myold.generics.base01;

/*

https://www.pdai.tech/md/java/basic/java-basic-x-generic.html
    泛型方法
 */
public class GenericsBase04 {

    /*
        <T>: 申明此方法持有一个类型T,也可以理解为声明此方法为泛型方法
         T: 指明该方法的返回值为类型T
     */
    public <T> T getObject(Class<T> c) throws InstantiationException, IllegalAccessException {
        return c.newInstance();
    }


}
