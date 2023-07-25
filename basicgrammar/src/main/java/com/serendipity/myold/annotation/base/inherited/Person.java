package com.serendipity.myold.annotation.base.inherited;

/**
 * ClassName Person
 * Description TODO
 * Author 11931
 * Date 2023-02-03:22:35
 * Version 1.0
 **/
@TestInheritedAnnotation(value = {"value"}, number = 10)
public class Person {

}

class Student extends Person {
}

