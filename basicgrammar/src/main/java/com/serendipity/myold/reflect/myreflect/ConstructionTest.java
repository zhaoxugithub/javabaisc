package com.serendipity.myold.reflect.myreflect;

import java.lang.reflect.InvocationTargetException;

public class ConstructionTest {

    class User {
        private int age;
        private String name;

        public User() {
        }

        public User(String name) {
            super();
            this.name = name;
        }

        /**
         * 私有构造
         *
         * @param age
         * @param name
         */
        private User(int age, String name) {
            super();
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" + "age=" + age + ", name='" + name + '\'' + '}';
        }

    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Class<?> clazz = Class.forName("com.serendipity.myold.reflect.myreflect.ConstructionTest$User");

        User user = (User) clazz.getConstructor().newInstance();
        user.setName("Jack");
        user.setAge(20);
        System.out.println(user);

    }
}
