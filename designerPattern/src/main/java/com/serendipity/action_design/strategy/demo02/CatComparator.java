package com.serendipity.action_design.strategy.demo02;

public class CatComparator implements Comparator<Cat> {
    @Override
    public int compareTo(Cat o1, Cat o2) {
        return o1.height == o2.height ? o1.weight - o2.weight : o1.height - o2.height;
    }
}
