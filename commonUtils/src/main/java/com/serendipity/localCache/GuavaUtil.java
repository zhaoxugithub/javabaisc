package com.serendipity.localCache;

import cn.hutool.core.annotation.scanner.EmptyAnnotationScanner;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaUtil {

    private static final Logger log = LoggerFactory.getLogger(GuavaUtil.class);

    public static void testGuava() throws ExecutionException, InterruptedException {
        // 创建一个缓存对象
        Cache<String, String> cache = CacheBuilder.newBuilder()
                // 设置缓存的初始容量
                .initialCapacity(5)
                // 设置缓存的最大容量
                .maximumSize(10)
                // 设置写缓存后多久过期
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .build();

        // 将数据放入缓存
        cache.put("key1", "value1");

        String key1 = cache.getIfPresent("key1");
        System.out.println(key1);

        // 获取缓存，如果获取不到就通过Callable 返回
        String key11 = cache.get("key1", () -> "key1 is not found.");
        System.out.println(key11);

        TimeUnit.SECONDS.sleep(4);
        String key12 = cache.get("key1", () -> "key1 is not found.");
        System.out.println(key12);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testGuava();
    }
}
