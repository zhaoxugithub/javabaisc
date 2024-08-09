package com.serendipity.myold.reflect.myreflect;

import java.lang.reflect.Field;

// https://www.pdai.tech/md/java/basic/java-basic-x-reflection.html
public class ReflectDemo03 {


    interface I1 {
    }

    interface I2 {
    }

    class Cell {
        public int mCellPublic;
    }

    class Animal extends Cell {
        private int mAnimalPrivate;
        protected int mAnimalProtected;
        int mAnimalDefault;
        public int mAnimalPublic;
        private static int sAnimalPrivate;
        protected static int sAnimalProtected;
        static int sAnimalDefault;
        public static int sAnimalPublic;
    }

    class Dog extends Animal implements I1, I2 {
        private int mDogPrivate;
        public int mDogPublic;
        protected int mDogProtected;
        private int mDogDefault;
        private static int sDogPrivate;
        protected static int sDogProtected;
        static int sDogDefault;
        public static int sDogPublic;

    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Class<Dog> dogClass = Dog.class;
        System.out.println(dogClass);
        // com.serendipity.myold.reflect.myreflect.ReflectDemo03$Dog
        System.out.println(dogClass.getName());
        // Dog
        System.out.println(dogClass.getSimpleName());
        // com.serendipity.myold.reflect.myreflect.ReflectDemo03$Dog
        System.out.println(dogClass.getCanonicalName());


        System.out.println(dogClass.isInterface());


        // 获取接口
        for (Class<?> anInterface : dogClass.getInterfaces()) {
            System.out.println(anInterface.getSimpleName());
        }

        // 获取父类
        System.out.println(dogClass.getSuperclass().getSimpleName());

        // 创建对象
        //Dog dog = dogClass.newInstance();

        // 获取字段
        for (Field field : dogClass.getFields()) {
            System.out.println(field.getName());
        }

        System.out.println("-------------------");


        // 获得某个类的自己声明的字段，即包括public、private和proteced，默认但是不包括父类声明的任何字段。
        for (Field declaredField : dogClass.getDeclaredFields()) {
            System.out.println(declaredField.getName());
        }
    }
}
