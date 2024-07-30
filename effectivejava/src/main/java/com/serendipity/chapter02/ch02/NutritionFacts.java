package com.serendipity.chapter02.ch02;

public class NutritionFacts {

    private final int servingSize; // (mL) required 必填
    private final int servings; // (per container) required 必填
    private final int calories; // (per serving) optional 选填
    private final int fat; // (g/serving) optional  选填
    private final int sodium; // (mg/serving) optional  选填
    private final int carbohydrate; // (g/serving) optional  选填

    // 通过我们会创建多个构造函数比如采用一下方式：可伸缩构造方法模式
    /*
        只使用参数比较小的暗中狂
     */
    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servings = servings;
        this.servingSize = servingSize;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }

    // 第二种方法是通过javaBean的方法
    /*
        可以进行get 但是没有办法进行set
     */
    public int getServingSize() {
        return servingSize;
    }

    public int getCalories() {
        return calories;
    }

    // 当参数特别多的时候可以使用builder方法
    // 针对很多不可变的对象
    /*
        public static class Builder {
        private final int servingSize; // (mL) required 必填
        private final int servings; // (per container) required 必填
        private int calories; // (per serving) optional 选填
        private int fat; // (g/serving) optional  选填
        private int sodium; // (mg/serving) optional  选填
        private int carbohydrate; // (g/serving) optional  选填
        public Builder(int servings) {
            this.servings = servings;
        }
        public Builder(int servingSize) {
            this.servingSize = servingSize;
        }
        public Builder(int servings, int servingSize) {
            this.servings = servings;
            this.servingSize = servingSize;
        }
    } */
}
