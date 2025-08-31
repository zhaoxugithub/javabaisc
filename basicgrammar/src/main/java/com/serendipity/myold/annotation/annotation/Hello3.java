package com.serendipity.myold.annotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,
        ElementType.FIELD})
public @interface Hello3 {
    int type() default 0;
    String level() default "info";
    String value() default "";
}
