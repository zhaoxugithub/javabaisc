package com.serendipity.myold.annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName HelloAnnotationDemo
 * Description TODO
 * Author 11931
 * Date 2023-12-02:23:49
 * Version 1.0
 **/
@Slf4j
@SuppressWarnings("all")
public class HelloAnnotationDemo {
    @Target(ElementType.TYPE)
    private @interface Hello1 {
        int type() default 0;

        String level() default "info";

        String value() default "";
    }

    @Target(ElementType.TYPE)
    private @interface Hellos {
        Hello1[] value();
    }

    /*
    一个重要的元注解@Retention定义了Annotation的生命周期：
    仅编译期：RetentionPolicy.SOURCE；
    仅class文件：RetentionPolicy.CLASS；
    运行期：RetentionPolicy.RUNTIME。
    如果@Retention不存在，则该Annotation默认为CLASS。因为通常我们自定义的Annotation都是RUNTIME，所以，务必要加上@Retention(RetentionPolicy.RUNTIME)这个元注解：
     */
    @Retention(RetentionPolicy.RUNTIME)
    private @interface Hello2 {
        int type() default 0;

        String level() default "info";

        String value() default "";
    }

    @Target({ElementType.METHOD,
            ElementType.FIELD})
    private @interface Hello3 {
        int type() default 0;

        String level() default "info";

        String value() default "";
    }

    @Hello2(type = 100, level = "level", value = "value")
    private int num = 10;

    @Hello2(type = 200, level = "level_method", value = "value_method")
    public void run() {
        System.out.println("run...");
    }

    @Test
    public void test() {
        // todo 解析
    }
}
