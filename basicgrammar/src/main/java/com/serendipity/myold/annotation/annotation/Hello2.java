package com.serendipity.myold.annotation.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
    一个重要的元注解@Retention定义了Annotation的生命周期：
    仅编译期：RetentionPolicy.SOURCE；
    仅class文件：RetentionPolicy.CLASS；
    运行期：RetentionPolicy.RUNTIME。
    如果@Retention不存在，则该Annotation默认为CLASS。因为通常我们自定义的Annotation都是RUNTIME，所以，务必要加上@Retention(RetentionPolicy.RUNTIME)这个元注解：
     */
@Retention(RetentionPolicy.RUNTIME)
public @interface Hello2 {
    int type() default 0;

    String level() default "info";

    String value() default "";
}
