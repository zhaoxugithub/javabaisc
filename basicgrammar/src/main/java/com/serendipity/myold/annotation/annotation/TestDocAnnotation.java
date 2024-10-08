package com.serendipity.myold.annotation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface TestDocAnnotation {
    String value() default "default";
}
