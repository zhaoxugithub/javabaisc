package com.serendipity.myold.base;

/**
 * 对象的加载顺数：
 * 父类静态 - 子类静态  - 父类非静态代码块 - 父类构造方法 - 子类非静态代码块  - 子类构造方法
 */
class Meal {
    Meal() {
        System.out.println("Meal()");
    }
}

class Bread {
    Bread() {
        System.out.println("Bread()");
    }
}

class Cheese {
    Cheese() {
        System.out.println("Cheese()");
    }
}

class Lettuce {
    Lettuce() {
        System.out.println("Lettuce()");
    }
}

class Lunch extends Meal {
    Lunch() {
        System.out.println("Lunch()");
    }
}

class PortableLunch extends Lunch {
    PortableLunch() {
        System.out.println("PortableLunch()");
    }
}

// Meal()  Lunch  PortableLunch  Bread   Cheese  Lettuce  Sandwich
public class Sandwich extends PortableLunch {
    private Bread b = new Bread();
    private Cheese c = new Cheese();
    private Lettuce l = new Lettuce();

    public Sandwich() {
        System.out.println("Sandwich()");
    }

    public static void main(String[] args) {
        new Sandwich();
        //meal()->lunch->portableLunch->bread()->cheese->lettuce->sandwich
    }
}