package com.serendipity.myold.annotation;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

@Slf4j
@SuppressWarnings("all")
public class TypeParameterAndTypeUseAnnotationDemo {

    // 自定义ElementType.TYPE_PARAMETER注解
    @Retention(RetentionPolicy.RUNTIME)
// 作用在方法参数中
    @Target(ElementType.TYPE_PARAMETER)
    @interface MyNotEmpty {
    }

    // 自定义ElementType.TYPE_USE注解
    @Retention(RetentionPolicy.RUNTIME)

    @Target(ElementType.TYPE_USE)
    @interface MyNotNull {
    }

    /*
    CONSTRUCTOR：构造器的声明
    FIELD：域声明（包括enum实例）
    LOCAL_VARIABLE：局部变量声明
    METHOD：方法声明
    PACKAGE：包声明
    PARAMETER：参数声明
    TYPE：类、接口（包括注解类型）或enum声明
    ANNOTATION_TYPE：注解声明（应用于另一个注解上）
    TYPE_PARAMETER：类型参数声明（1.8新加入）
    TYPE_USE：类型使用声明（1.8新加入）
     */
    private class TypeParameterAndTypeUseAnnotation<@MyNotEmpty T> {
        // 使用TYPE_PARAMETER类型，会编译不通过
//		public @MyNotEmpty T test(@MyNotEmpty T a){
//			new ArrayList<@MyNotEmpty String>();
//				return a;
//		}
        // 使用TYPE_USE类型，编译通过
        public @MyNotNull T test2(@MyNotNull T a) {
            new ArrayList<@MyNotNull String>();
            return a;
        }
    }
}
