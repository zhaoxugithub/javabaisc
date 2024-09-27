package com.serendipity.myold.hutools;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

/**
 * ClassName HutoolCoreIOUtil
 * Description TODO
 * Author 11931
 * Date 2023-07-29:1:25
 * Version 1.0
 **/
@Slf4j
@SuppressWarnings("all")
public class HutoolCoreIOUtil {
    @Test
    public void test01() {
        BufferedInputStream inputStream = FileUtil.getInputStream("HutoolCoreConvert.java");
        BufferedOutputStream outputStream = FileUtil.getOutputStream("HutoolCoreConvertCopy.java");
        IoUtil.copy(inputStream, outputStream, IoUtil.DEFAULT_BUFFER_SIZE);
    }
}
