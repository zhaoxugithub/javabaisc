package com.serendipity.myold.annotation.entity;

import com.serendipity.myold.annotation.annotation.TestInheritedAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data 可以被继承
@AllArgsConstructor
@NoArgsConstructor
@Data
@TestInheritedAnnotation(values = {"value"}, number = 10)
public class Person {
    private String name = "PersonName";
    public static int age = 20;
    public String remark = "PersonRemark";
    public String email = "PersonEmail";

    public void sayHello() {
        System.out.println("Hello, " + name + "!");
    }

    private void fun() {
        System.out.println("fun");
    }
}
