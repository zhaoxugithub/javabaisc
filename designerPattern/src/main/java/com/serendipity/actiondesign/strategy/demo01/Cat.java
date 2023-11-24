package com.serendipity.actiondesign.strategy.demo01;

/**
 * 所有想进行的比较的类都要实现comparable接口
 */
public class Cat implements Comparable<Cat> {

    private int weight;
    private int height;


    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public int compare(Cat o) {
        return this.weight == o.weight ? (this.height - o.height) : this.height - o.height;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }
}
