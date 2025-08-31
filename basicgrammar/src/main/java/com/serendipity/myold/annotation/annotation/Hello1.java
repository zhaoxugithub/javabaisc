package com.serendipity.myold.annotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Hello1 {
    int type() default 0;
    String level() default "info";
    String value() default "";
}
