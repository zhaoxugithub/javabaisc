package com.serendipity.myold.enums;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/4 0:21
 * FileName: EnumsDemo03
 * Description: com.enums
 */
/*
Java使用enum定义枚举类型，它被编译器编译为final class Xxx extends Enum { … }；
通过name()获取常量定义的字符串，注意不要使用toString()；
通过ordinal()返回常量定义的顺序（无实质意义）；
可以为enum编写构造方法、字段和方法
enum的构造方法要声明为private，字段强烈建议声明为final；
enum适合用在switch语句中。
 */
@Slf4j
@SuppressWarnings("all")
public class EnumsDemo02 {
    enum WeekDay01 {
        SUN, MON, TUE, WED, THU, FRI, SAT;
    }

    // 方法二
    enum WeekDay02 {
        MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6), SUN(0);
        public final int dayValue;

        private WeekDay02(int dataValue) {
            this.dayValue = dataValue;
        }
    }

    enum WeekDay03 {
        MON(1, "星期一"), TUE(2, "星期二"), WED(3, "星期三"), THU(4, "星期四"), FRI(5, "星期五"), SAT(6, "星期六"), SUN(0, "星期日");
        public final int dataValue;
        private final String chinese;

        // 设置成私有的防止被new出来的
        private WeekDay03(int dataValue, String chinese) {
            this.dataValue = dataValue;
            this.chinese = chinese;
        }

        public String getChinese() {
            return chinese;
        }

        @Override
        public String toString() {
            return "WeekDay03{" + "dataValue=" + dataValue + ", chinese='" + chinese + '\'' + '}';
        }
    }

    @Test
    public void test() {
        // 所有美剧类型
        WeekDay01[] values = WeekDay01.values();
        // 区分大小写
        WeekDay01 sun = WeekDay01.valueOf("SUN");
        System.out.println(sun.name());

        for (WeekDay01 value : values) {
            System.out.println(value.name());
        }
    }

    @Test
    public void test2() {
        WeekDay02[] values = WeekDay02.values();
        WeekDay02 mon1 = WeekDay02.valueOf("MON");
        for (WeekDay02 value : values) {
            System.out.println(value.dayValue);
        }
        WeekDay02 mon = WeekDay02.MON;
        int dayValue = mon.dayValue;
    }

    @Test
    public void test3() {
        WeekDay03[] values = WeekDay03.values();
        WeekDay03 mon = WeekDay03.valueOf("MON");

        for (WeekDay03 value : values) {
            System.out.println(value.dataValue);
            System.out.println(value.getChinese());
        }

        WeekDay03 mon1 = WeekDay03.MON;
        String chinese = mon1.getChinese();
        int dataValue = mon1.dataValue;

        System.out.println(chinese + "---" + dataValue);
    }


    public static void main(String[] args) {
        WeekDay01 mon = WeekDay01.MON;
        String name = mon.name();
        System.out.println(name); // MON
        System.out.println(mon);  // MON

        // 返回枚举的顺序
        int ordinal = mon.ordinal();
        System.out.println(ordinal);
        // 返回的顺序，会随着枚举的顺序发生变化
        // 如果在代码中编写了类似if(x.ordinal()==1)这样的语句，就要保证enum的枚举顺序不能变。
        // 新增的常量必须放在最后。
        // mon.ordinal 的顺序是随着枚举的顺序而发生改变的,如果想防止这种变化的话,可以采取方法二.自定义固定顺序
        WeekDay02 mon1 = WeekDay02.MON;
        int dayValue = mon1.dayValue;
        System.out.println(dayValue);
        System.out.println("----------------------------------------------");
        WeekDay03 day = WeekDay03.SUN;
        switch (day) {
            case MON:
            case TUE:
            case WED:
            case THU:
            case FRI:
                System.out.println("Today is " + day + ". Work at office!");
                break;
            case SAT:
            case SUN:
                System.out.println("Today is " + day + ". Work at home!");
                System.out.println(day.toString());
                System.out.println(day.dataValue);
                System.out.println(day.name());
                System.out.println(day.getChinese());
                break;
            default:
                throw new RuntimeException("cannot process " + day);
        }
    }
}
