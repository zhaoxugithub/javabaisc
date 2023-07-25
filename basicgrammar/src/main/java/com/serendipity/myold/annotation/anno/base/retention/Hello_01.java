package com.serendipity.myold.annotation.anno.base.retention;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/11 22:31
 * FileName: Hello_01
 * Description: com.anno.base.retention
 */
/*

一个重要的元注解@Retention定义了Annotation的生命周期：

仅编译期：RetentionPolicy.SOURCE；
仅class文件：RetentionPolicy.CLASS；
运行期：RetentionPolicy.RUNTIME。
如果@Retention不存在，则该Annotation默认为CLASS。因为通常我们自定义的Annotation都是RUNTIME，所以，务必要加上@Retention(RetentionPolicy.RUNTIME)这个元注解：
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Hello_01 {
    int type() default 0;

    String level() default "info";

    String value() default "";
}
