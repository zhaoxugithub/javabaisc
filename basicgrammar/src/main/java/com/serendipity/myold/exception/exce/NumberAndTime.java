package com.serendipity.myold.exception.exce;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName NumberAndTime
 * Description TODO
 * Author 11931
 * Date 2022-11-04:19:55
 * Version 1.0
 * 数值计算和时间计算
 **/
@SuppressWarnings("all")
public class NumberAndTime {
    /*
        scale 保留多少位小数
     */
    private static void scaleProble() {
        BigDecimal decimal = new BigDecimal("12.226");
        // decimal.setScale(2); 会报出异常
        BigDecimal result = decimal.setScale(12);
        System.out.println(result);
        // 向上取整（四舍五入）
        BigDecimal bigDecimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal);
    }

    /*
        BigDecimal 做除法是出现除不尽的情况
     */
    private static void divideProblem() {
        // 会出现异常ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        // 除法除不尽，这里会报错
        // System.out.println(new BigDecimal(30).divide(new BigDecimal(7)));
        // 30/7 = 4.28，，，，向上取整（四舍五入）
        System.out.println(new BigDecimal(30).divide(new BigDecimal(7),
                2, BigDecimal.ROUND_HALF_UP));
    }

    /*
        精度问题导致比较结果和预期的不一致
     */
    private static void equalProblem() {
        BigDecimal decimal1 = new BigDecimal("0");
        BigDecimal decimal2 = new BigDecimal("0.0");
        // false
        System.out.println(decimal2.equals(decimal1));
        // 相同
        System.out.println(decimal2.compareTo(decimal1) == 0);
    }

    /*
        SimpleDateFormat 可以解析大于/等于它定义的时间精度
        可以解析大于/等于他定义的时间精度，但是不能解析小于它定义的时间精度
     */
    private static void formatPrecision() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time_x = "2020-03-01 10:00:00";
        String time = "2020-03";
        // format.parse(time)这行会报出异常Unparseable date: "2020-03"
        System.out.println(format.parse(time));
        System.out.println(format.parse(time_x));
    }

    /*
        SimpleDateFormat 存在线程安全问题
        线程不安全，所以下面这段代码会抛出异常
        正确的做法是
                1.使用局部变量：  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 定义成线程局部变量
                2.使用threadLocal
                3.使用synchronize

             优先使用局部变量
     */
    private static void threadSafety() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 创建一个线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10, 100, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(1000)
        );
        while (true) {
            threadPoolExecutor.execute(() -> {
                String dateStr = "2020-03-01 10:00:00";
                try {
                    Date parse = sdf.parse(dateStr);
                    String format = sdf.format(parse);
                    // 比较转换之后的时间字符串是否相等
                    System.out.println(dateStr.equals(format));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) throws ParseException {
        scaleProble();
        // // divideProblem();
        // // equalProblem();
        // // formatPrecision();
        // threadSafety();
    }
}
