package com.serendipity.myold.base.oriented;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


// Meal()  Lunch  PortableLunch  Bread   Cheese  Lettuce  Sandwich
@Slf4j
@SuppressWarnings("all")
public class SandwichDemo {
    /**
     * 对象的加载顺数：
     * 父类静态 - 子类静态  - 父类非静态代码块 - 父类构造方法 - 子类非静态代码块  - 子类构造方法
     */
    private class Meal {
        Meal() {
            System.out.println("Meal()");
        }
    }

    private class Bread {
        Bread() {
            System.out.println("Bread()");
        }
    }

    private class Cheese {
        Cheese() {
            System.out.println("Cheese()");
        }
    }

    private class Lettuce {
        Lettuce() {
            System.out.println("Lettuce()");
        }
    }

    private class Lunch extends Meal {
        Lunch() {
            System.out.println("Lunch()");
        }
    }

    private class PortableLunch extends Lunch {
        PortableLunch() {
            System.out.println("PortableLunch()");
        }
    }

    private class Sandwich extends PortableLunch {
        private Bread b = new Bread();
        private Cheese c = new Cheese();
        private Lettuce l = new Lettuce();

        public Sandwich() {
            System.out.println("Sandwich()");
        }
    }

    @Test
    public void test01() {
        new Sandwich();
        // meal()->lunch->portableLunch->bread()->cheese->lettuce->sandwich
    }
}