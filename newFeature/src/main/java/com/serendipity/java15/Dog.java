package com.serendipity.java15;

// 因为 Animal 类使用了 sealed 关键字，所以 Dog 类必须使用 permits 关键字指定可以继承的类
// 并且使用 final 关键字修饰 Dog 类，表示 Dog 类不能被继承,如果想要Dog类被继承，可以使用sealed关键字
public sealed class Dog extends Animal permits Hashiqi{
}
