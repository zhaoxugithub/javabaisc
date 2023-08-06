package com.serendipity.myold.hutools;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

/**
 * ClassName HutoolCoreIOUtil
 * Description TODO
 * Author 11931
 * Date 2023-07-29:1:25
 * Version 1.0
 **/
public class HutoolCoreIOUtil {

    @Test
    public void test01() {
        BufferedInputStream inputStream = FileUtil.getInputStream("HutoolCoreConvert.java");
        BufferedOutputStream outputStream = FileUtil.getOutputStream("HutoolCoreConvertCopy.java");
        IoUtil.copy(inputStream, outputStream, IoUtil.DEFAULT_BUFFER_SIZE);
    }
}
