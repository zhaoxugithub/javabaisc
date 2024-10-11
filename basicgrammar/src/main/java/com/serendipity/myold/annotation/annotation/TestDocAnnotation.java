package com.serendipity.myold.annotation.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface TestDocAnnotation {
    String value() default "default";
}
