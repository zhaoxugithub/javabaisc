package com.serendipity.myold.reflect;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Slf4j
@SuppressWarnings("all")
public class ClassAPI {

    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("com.old.reflect.Student");
        // 获取成员变量、包括子类及父类的成员变量，同时只能包含公共变量public
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            // 获取类中的成员变量
            // field Modidfiers,变量修饰符
            // 什么都不加 是0 ， public  是1 ，private 是 2 ，protected 是 4，static 是 8 ，final 是 16
            log.info("field = {}", field.toString());
            // getDeclaringClass() 获取成员变量的的所属类
            log.info("fieldName={},fieldType={},fieldModifiers = {},FieldDeclaringClass={}", field.getName(), field.getType(), field.getModifiers(), field.getDeclaringClass());
        }
        System.out.println("===============================");
        // 此方法返回的是当前类（不包含父类）的所有属性，不仅仅局限于 公共访问修饰符，所有的访问修饰符都可以拿到
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            log.info("field={}", field);
        }
        System.out.println("===============================");
        // 反在在一定程度上破坏了封装性，需要合理使用,获取private 变量
        Field address = aClass.getDeclaredField("address");
        // 设置该属性是否能被访问，true表示能被访问，破坏了封装型
        address.setAccessible(true);
        log.info("fieldName={}", address.getName());
        Object o = aClass.newInstance();
        if (o instanceof Student) {
            address.set(o, "北京");
            Student student = (Student) o;
            log.info("student address={}", student.getAddress());
        }
        System.out.println("=================================");
        // 获取成员方法、包括子类及父类，同时只能包含公共方法
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            log.info("class = {},method = {}", method.getDeclaringClass(), method.getName());
        }
        System.out.println("=================================");
        // 获取当前类的所有方法，包括私有方法
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            log.info("class = {},method = {}", method.getDeclaringClass(), method.getName());
        }
        Method add = aClass.getDeclaredMethod("add", Integer.class, Integer.class);
        add.setAccessible(true);
        Object o1 = aClass.newInstance();
        add.invoke(o1, 123, 133);

        System.out.println("===============构造方法==================");
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor constructor : constructors) {
            log.info("constructor={}", constructor.getName());
        }
        System.out.println("------------------");
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            System.out.println(constructor);
        }
        System.out.println("-----------------");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class, int.class, String.class);
        declaredConstructor.setAccessible(true);
        Student ss = (Student) declaredConstructor.newInstance("22", 22, "ss");
        System.out.println(ss);
    }
}
