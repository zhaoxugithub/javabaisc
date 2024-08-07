package com.serendipity.learn.myjvm;

import org.junit.Test;

public class ClassLoaderTest {

    @Test
    public void test() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        // 实际上这个是BootStrapLoader
        System.out.println(loader.getParent().getParent());
    }


    /*
        类加载：
            1、命令行启动应用时候由JVM初始化加载
            2、通过Class.forName()方法动态加载
            3、通过ClassLoader.loadClass()方法动态加载

            Class.forName(): 将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块；
            ClassLoader.loadClass(): 只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,只有在newInstance才会去执行static块
     */
    @Test
    public void test02() throws ClassNotFoundException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);

        //使用ClassLoader.loadClass()来加载类，不会执行初始化
        // Class<?> test2 = loader.loadClass("com.serendipity.learn.myjvm.Test2");

        //使用Class.forName()来加载类，默认会执行初始化块
//        Class<?> aClass = Class.forName("com.serendipity.learn.myjvm.Test2");

        // 会进行初始化
        Class.forName("com.serendipity.learn.myjvm.Test2", true, loader);
        // 不会进行初始化
        Class.forName("com.serendipity.learn.myjvm.Test2", false, loader);
    }
}
