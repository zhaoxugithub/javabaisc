package com.serendipity.myold.annotation.base.inherited;

import java.lang.annotation.*;

/*
Inherited注解的作用：被它修饰的Annotation将具有继承性。如果某个类使用了被@Inherited修饰的Annotation，则其子类将自动具有该注解。
 */
@Inherited // 注解可以被继承
@Retention(RetentionPolicy.RUNTIME) // 运行期间保留
@Target({ElementType.TYPE, ElementType.METHOD})  // 注解作用方法，类、接口、枚举类
public @interface TestInheritedAnnotation {
    String[] value();

    int number();
}
