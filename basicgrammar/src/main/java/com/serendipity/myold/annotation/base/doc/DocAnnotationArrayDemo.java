package com.serendipity.myold.annotation.base.doc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;

/**
 * ClassName Test
 * Description TODO
 * Author 11931
 * Date 2023-02-03:22:18
 * Version 1.0
 **/
@Slf4j
@SuppressWarnings("all")
public class DocAnnotationArrayDemo {
    @Documented
    @Target({ElementType.METHOD, ElementType.TYPE})
    @interface TestDocAnnotation {
        String value() default "default";
    }

    @TestDocAnnotation("mytestDoc")
    public void testDoc() {
        ArrayList<Object> objects = new ArrayList<>();
        int[] ints = new int[10];
    }

    @Test
    public void test() {
        DocAnnotationArrayDemo test = new DocAnnotationArrayDemo();
        test.testDoc();
    }

}
