package com.serendipity.myold.exception.exce;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName GeneralException
 * Description TODO
 * Author 11931
 * Date 2022-10-27:1:19
 * Version 1.0
 **/
@SuppressWarnings("all")
@Slf4j
public class GeneralException {

    public static class User {

        private String name;

        public User() {
        }

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


    public static class Manager extends User {
    }

    public static class Worker extends User {
        private String name;

        public Worker() {
        }

        public Worker(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Worker{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    // 并发修改异常
    private static void concurrentModificationException(ArrayList<User> users) {

        // 如果直接使用for循环会触发并发修改异常
        /*
            java中的fail fast机制
            迭代器工作在一个独立的线程中
         */
        for (User user : users) {
            if (user.getName().equals("imooc")) {
                users.remove(user);
            }
        }

        // 如果使用迭代器就没有问题
        // Iterator<User> iterator = users.iterator();
        // while (iterator.hasNext()) {
        //     User user = iterator.next();
        //     if (user.getName().equals("imooc")) {
        //         iterator.remove();
        //     }
        // }
    }

    // 创建一个StaffType.values.lenth 长度的hashMap
    public static final Map<String, StaffType> typeIndex = new HashMap<>(
            StaffType.values().length
    );

    static {
        for (StaffType value : StaffType.values()) {
            typeIndex.put(value.name(), value);
        }
    }

    private static StaffType enumFind(String type) {

        // 这个如果type类型不存在就会报IllegalArgumentException异常
        // return StaffType.valueOf(type);

        // 如何解决这个问题：
        // 方法一：try-catch
        try {
            return StaffType.valueOf(type);
        } catch (IllegalArgumentException e) {
            log.info("exception={}", e);
            return null;
        }

        // 方法二：改进的实现，但是效率不高1
       /* for (StaffType value : StaffType.values()) {
            if (value.name().equals(type)) {
                return value;
            }
        }
        return null;*/

        // 3.静态Map索引，只有一次循环枚举的过程
        // return typeIndex.get(type);

        // 4.使用Google Guava Enums,需要相关的依赖
        // return Enums.getIfPresent(StaffType.class, type).orNull();
    }


    public static void main(String[] args) {
        // 1.并发修改异常
        // ArrayList<User> users = new ArrayList<User>(
        //         Arrays.asList(new User("qinyi"), new User("imooc"))
        // );
        // concurrentModificationException(users);
        // ArrayList<User> works = new ArrayList<>(
        //         Arrays.asList(new Worker("aa"), new Worker("imooc"))
        // );
        // concurrentModificationException(works);
        StaffType ss = enumFind("ss");
        System.out.println(ss);
        //
        // System.out.println("-----------------------");
        // User user1 = new Manager();
        // User user2 = new Worker();

        // Manager m1 = (Manager) user1;
        // Manager m2 = (Manager) user2;

        // System.out.println(user2.getClass().getName());
        // System.out.println(user2 instanceof Manager);

        // 3. 枚举查找异常
        // System.out.println(enumFind("RD"));
        // System.out.println(enumFind("abc"));
    }
}
