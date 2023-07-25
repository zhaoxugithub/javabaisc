package com.serendipity.myold.annotation.anno.base.target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/11 22:16
 * FileName: Hello_01
 * Description: com.anno.base
 */
/*

最常用的元注解是@Target。使用@Target可以定义Annotation能够被应用于源码的哪些位置：

类或接口：ElementType.TYPE；
字段：ElementType.FIELD；
方法：ElementType.METHOD；
构造方法：ElementType.CONSTRUCTOR；
方法参数：ElementType.PARAMETER。

 */
@Target({ElementType.METHOD,
        ElementType.FIELD})
public @interface Hello_01 {
    int type() default 0;

    String level() default "info";

    String value() default "";
}
