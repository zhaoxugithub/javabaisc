package com.serendipity.myold.hutools;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ClassName HutoolCoreConvert
 * Description TODO
 * Author 11931
 * Date 2023-07-29:0:38
 * Version 1.0
 **/
public class HutoolCoreConvert {

    /**
     * 转成string
     */
    @Test
    public void test01() {
        int a = 1;
        String aStr = Convert.toStr(a);
        System.out.println(aStr);

        long[] b = {1, 2, 3, 4, 5};
        String bStr = Convert.toStr(b);
        System.out.println(bStr);
    }

    /**
     * 转成数组
     */
    @Test
    public void test02() {
        String[] b = {"1", "2", "3", "4", "5"};
        Integer[] intArray = Convert.toIntArray(b);
        System.out.println(Arrays.toString(intArray));

        long[] c = {1, 2, 3, 4, 5, 6};
        Integer[] intArray1 = Convert.toIntArray(c);
        System.out.println(Arrays.toString(intArray1));
    }

    @Test
    public void test03() {
        String a = "2017-05-06";
        Date date = Convert.toDate(a);
        System.out.println(date);

        Object[] objects = {"a", "你", "好", "", 1};
        List<?> list = Convert.toList(objects);
        System.out.println(list);
    }


    /**
     * Unicode和字符串转换
     */
    @Test
    public void test04() {
        String a = "我是一个小小的可爱的字符串";
        String unicode = Convert.strToUnicode(a);
        System.out.println(unicode);
        String raw = Convert.unicodeToStr(unicode);
        Assert.assertEquals("ok", a, raw);
    }

    /**
     * 编码转换
     */
    @Test
    public void test05() {
        String a = "我不是乱码";
        // 原来是utf8 -> iso88591
        String result = Convert.convertCharset(a, CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        // iso88591 -> utf8
        String raw = Convert.convertCharset(result, CharsetUtil.ISO_8859_1, "UTF-8");
        Assert.assertEquals(raw, a);
    }

    // 计算时间
    @Test
    public void test06() {
        long a = 4535345;
        long l = Convert.convertTime(a, TimeUnit.SECONDS, TimeUnit.MINUTES);
        System.out.println(l);
    }

    // 金额大小写转换
    @Test
    public void test07() {
        double a = 1234.555;
        String s = Convert.digitToChinese(a);
        System.out.println(s);

        // ONE HUNDRED AND CENTS TWENTY THREE ONLY
        String format = Convert.numberToWord(100.23);
        System.out.println(format);
        // 1.2k
        String format1 = Convert.numberToSimple(1200);
        System.out.println(format1);
    }
}
