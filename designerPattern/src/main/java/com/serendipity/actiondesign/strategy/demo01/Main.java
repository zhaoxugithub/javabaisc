package com.serendipity.actiondesign.strategy.demo01;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {

        Cat[] cats = {new Cat(1, 2), new Cat(3, 5),
                new Cat(2, 4), new Cat(5, 0)};

        new Sort().sort(cats);

        Arrays.asList(cats).forEach(x -> System.out.println(x));

    }

}
