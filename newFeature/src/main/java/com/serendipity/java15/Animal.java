package com.serendipity.java15;

/**
 * 只希望 Dog 和 Cat 类可以继承 Animal 类，而不希望其他类继承 Animal 类
 * 需要使用密封类,关键字为 sealed
 */
public sealed class Animal permits Dog, Cat {
    public void eat() {
    }

}
