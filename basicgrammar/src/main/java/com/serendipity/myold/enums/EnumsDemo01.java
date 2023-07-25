package com.serendipity.myold.enums;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/1 12:31
 * FileName: EmumsDemo01
 * Description: com.enums
 */


/*
使用常量的时候，可以这么引用：

if (day == Weekday.SAT || day == Weekday.SUN) {
    // TODO: work at home
}
也可以把常量定义为字符串类型，例如，定义3种颜色的常量：

public class Color {
    public static final String RED = "r";
    public static final String GREEN = "g";
    public static final String BLUE = "b";
}
使用常量的时候，可以这么引用：

String color = ...
if (Color.RED.equals(color)) {
    // TODO:
}

无论是int常量还是String常量，使用这些常量来表示一组枚举值的时候，有一个严重的问题就是，编译器无法检查每个值的合理性。例如：

 if (weekday == 6 || weekday == 7) {
    if (tasks == Weekday.MON) {
        // TODO:
    }
}

注意到Weekday定义的常量范围是0~6，并不包含7，编译器无法检查不在枚举中的int值；
定义的常量仍可与其他变量比较，但其用途并非是枚举星期值。
 */
public class EnumsDemo01 {
    public static final int SUN = 0;
    public static final int MON = 1;
    public static final int TUE = 2;
    public static final int WED = 3;
    public static final int THU = 4;
    public static final int FRI = 5;
    public static final int SAT = 6;
}


