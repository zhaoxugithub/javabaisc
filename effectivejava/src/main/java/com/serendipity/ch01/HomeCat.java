package com.serendipity.ch01;

/**
 * ClassName HomeCat
 * Description TODO
 * Author 11931
 * Date 2022/9/9:0:48
 * Version 1.0
 **/
class HomeCat extends Cat {
    public HomeCat() {
        super();
        // 这里初始化调用父类的构造器会有问题，因为父类的构造器是私有的
        // super();
    }
}
