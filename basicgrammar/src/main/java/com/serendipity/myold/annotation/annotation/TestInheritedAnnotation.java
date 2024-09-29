package com.serendipity.myold.annotation.annotation;

import java.lang.annotation.*;

/*
   这四个是元注解:
    是否可以继承;
    注解保留范围：运行期，编译期，源文件期间
    作用范围： 方法，类，方法参数等等
 * @Documented
 * @Inherited 具有继承
 * @Retention(RetentionPolicy.RUNTIME) 在运行时有效
 * @Target({ElementType.TYPE, ElementType.METHOD})作用于方法和类上
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface TestInheritedAnnotation {
    String[] values();

    int number();
}