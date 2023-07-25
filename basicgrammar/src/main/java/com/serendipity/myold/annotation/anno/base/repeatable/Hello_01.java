package com.serendipity.myold.annotation.anno.base.repeatable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/11 22:33
 * FileName: Hello_10
 * Description: com.anno.base.repeatable
 *
 * 使用@Repeatable这个元注解可以定义Annotation是否可重复。这个注解应用不是特别广泛。
 */
//@Repeatable(Hellos_01.class)
@Target(ElementType.TYPE)
public @interface Hello_01 {
    int type() default 0;

    String level() default "info";

    String value() default "";
}
