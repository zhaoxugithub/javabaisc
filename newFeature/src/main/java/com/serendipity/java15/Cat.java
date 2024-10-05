package com.serendipity.java15;

// 因为 Animal 类使用了 sealed 关键字，所以 Cat 类必须使用 permits 关键字指定可以继承的类
// 如果不希望 Cat 类被继承，可以使用 final 关键字修饰 Cat 类
public final class Cat extends Animal {
}
