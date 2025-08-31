package com.serendipity.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CaffeineUtil {
    public static void testCaffeine() throws InterruptedException {
        Cache<String, String> cache = Caffeine.newBuilder()
                // 设置缓存的初始容量
                .initialCapacity(5)
                // 设置缓存的最大容量，超出就淘汰
                .maximumSize(10)
                // 设置写缓存后多久过期
                .expireAfterWrite(3, TimeUnit.SECONDS).build();
        // 写入缓存数据
        cache.put("key1", "value1");
        String key1 = cache.get("key1", k -> {
            System.out.println(k);
            return "key1 is not found.";
        });
        System.out.println(key1);
        TimeUnit.SECONDS.sleep(4);
        String key2 = cache.get("key1", k -> {
            System.out.println(k);
            return "key1 is not found.";
        });
        log.info(key1);
        System.out.println(key2);
    }

    public static void main(String[] args) throws InterruptedException {
        testCaffeine();
    }
}
