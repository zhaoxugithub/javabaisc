package com.serendipity.myold.annotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Hellos {
    Hello1[] value();
}
