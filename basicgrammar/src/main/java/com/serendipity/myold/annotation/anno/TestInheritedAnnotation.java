package com.serendipity.myold.annotation.anno;

import java.lang.annotation.*;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/18 10:36 上午
 * FileName: TestInheritedAnnotation
 * Description: com.anno
 *
 * @Inherited 具有继承
 * @Retention(RetentionPolicy.RUNTIME) 在运行时有效
 * @Target({ElementType.TYPE, ElementType.METHOD})作用于方法和类上
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface TestInheritedAnnotation {

    String[] values();

    int number();
}
