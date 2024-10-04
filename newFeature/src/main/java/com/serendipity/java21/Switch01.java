package com.serendipity.java21;


/**
 * jdk12
 */
public class Switch01 {

    public static void main(String[] args) {
        test01(2);
        test02(2);
        System.out.println(test03(2));
    }

    // jdk12 以前
    public static void test01(int month) {
        switch (month) {
            case 1:
            case 2:
            case 3:
                System.out.println("January");
                break;
            case 4:
            case 5:
            case 6:
                System.out.println("February");
                break;
            case 7:
            case 8:
            case 9:
                System.out.println("March");
                break;
            default:
                System.out.println("Invalid month");
                break;
        }
    }

    // jdk12
    public static void test02(int month) {
        switch (month) {
            case 1, 2, 3 -> System.out.println("January");
            case 4, 5, 6 -> System.out.println("February");
            case 7, 8, 9 -> System.out.println("March");
            default -> System.out.println("Invalid month");
        }
    }

    // jdk13: 带有返回值的switch
    public static String test03(int month) {
        return switch (month) {
            case 1, 2, 3 -> "January";
            case 4, 5, 6 -> "February";
            case 7, 8, 9 -> "March";
            default -> "Invalid month";
        };
    }
}
