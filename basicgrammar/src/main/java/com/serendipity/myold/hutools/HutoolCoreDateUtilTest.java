package com.serendipity.myold.hutools;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@SuppressWarnings("all")
public class HutoolCoreDateUtilTest {
    // Calendar -> DateTime
    // long -> DateTime
    // xxx -> String
    @Test
    public void test01() {
        // 当前时间
        DateTime date1 = DateUtil.date();
        log.info(String.valueOf(date1));
        DateTime date2 = DateUtil.date(Calendar.getInstance());
        log.info(String.valueOf(date2));
        DateTime date3 = DateUtil.date(System.currentTimeMillis());
        log.info(String.valueOf(date3));
        String now = DateUtil.now();
        log.info(now);
        String today = DateUtil.today();
        log.info(today);
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
