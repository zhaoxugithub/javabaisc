package com.serendipity.myold.hutools;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class HutoolCoreDateUtilTest {
    // Calendar -> DateTime
    // long -> DateTime
    // xxx -> String
    @Test
    public void test01() {
        // 当前时间
        DateTime date1 = DateUtil.date();
        System.out.println(date1);
        DateTime date2 = DateUtil.date(Calendar.getInstance());
        System.out.println(date2);
        DateTime date3 = DateUtil.date(System.currentTimeMillis());
        System.out.println(date3);
        String now = DateUtil.now();
        System.out.println(now);
        String today = DateUtil.today();
        System.out.println(today);
    }

    // str -> DateTime
    @Test
    public void test02() {
        String dateStr = "2017-03-01";
        DateTime parse = DateUtil.parse(dateStr);
        System.out.println(parse);
        Date date = DateUtil.parse(dateStr);
        // 结果 2017/03/01
        String format = DateUtil.format(date, "yyyy/MM/dd");
        System.out.println(format);
        // 常用格式的格式化，结果：2017-03-01
        String formatDate = DateUtil.formatDate(date);
        System.out.println(formatDate);
        // 结果：2017-03-01 00:00:00
        String formatDateTime = DateUtil.formatDateTime(date);
        System.out.println(formatDateTime);
        // 结果：00:00:00
        String formatTime = DateUtil.formatTime(date);
        System.out.println(formatTime);
    }
}
