package com.serendipity.myold.base;

import java.time.*;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/20 0:11
 * FileName: DateTimeAPI
 * Description: com.java8.base
 */
public class DateTimeDemo {

    public static void main(String[] args) {
        // GET the System clock as UTC offset
        // UTC 世界协调时间。与北京的东八区CST 慢 8个小时
        // UTC+8 = CST
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());
        // Get the local date and local time
        final LocalDate now = LocalDate.now();
        final LocalDate now1 = LocalDate.now(clock);
        System.out.println(now);
        System.out.println(now1);
        // Get the local date and local TIme
        LocalTime now2 = LocalTime.now();
        LocalTime now3 = LocalTime.now(clock);
        System.out.println(now2);
        System.out.println(now3);
        // 默认是系统时间
        ZonedDateTime now4 = ZonedDateTime.now();
        // UTC时间
        ZonedDateTime now5 = ZonedDateTime.now(clock);
        System.out.println(ZonedDateTime.now(Clock.systemDefaultZone()));
        // 美国时间
        ZonedDateTime now6 = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(now4);
        System.out.println(now5);
        System.out.println(now6);
        LocalDateTime of = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
        LocalDateTime of1 = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);
        final Duration of2 = Duration.between(of, of1);
        long l = of2.toDays();
        long l1 = of2.toHours();
        System.out.println(l);
        System.out.println(l1);
    }
}
