package com.serendipity.actiondesign.strategy.demo02;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {

        Cat[] cats = {new Cat(1, 2), new Cat(3, 5),
                new Cat(2, 4), new Cat(5, 0)};

        new Sort<Cat>().sort(cats, new CatComparator());

        Arrays.asList(cats).forEach(x -> System.out.println(x));


        new Sort<Cat>().sort(cats, new Comparator<Cat>() {
            @Override
            public int compareTo(Cat o1, Cat o2) {
                return 0;
            }
        });

    }

}
