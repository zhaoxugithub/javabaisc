package com.serendipity.create_design.prototype.demo2;

//对于成员变量中是引用类型,克隆出来的对象中的引用依旧指向原来的对象地址
public class Client {

    public static void main(String[] args) {


        System.out.println("原型模式完成对象的创建");
        // TODO Auto-generated method stub
        Sheep sheep = new Sheep("tom", 1, "白色");

        sheep.friend = new Sheep("jack", 2, "黑色");


        Sheep sheep2 = (Sheep) sheep.clone();
        Sheep sheep3 = (Sheep) sheep.clone();
        Sheep sheep4 = (Sheep) sheep.clone();
        Sheep sheep5 = (Sheep) sheep.clone();


        System.out.println(sheep);
        System.out.println(sheep2);
        System.out.println(sheep3 == sheep2);


        System.out.println("sheep2 =" + sheep2 + "sheep2.friend=" + sheep2.friend.hashCode());
        System.out.println("sheep3 =" + sheep3 + "sheep3.friend=" + sheep3.friend.hashCode());
        System.out.println("sheep4 =" + sheep4 + "sheep4.friend=" + sheep4.friend.hashCode());
        System.out.println("sheep5 =" + sheep5 + "sheep5.friend=" + sheep5.friend.hashCode());

    }
}
