package com.serendipity.hutools.cache;


import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LRUCache;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

public class LRUTest {
    // LRU: 最近最久未使用缓存
    @Test
    public void LRUTest01() {

        LRUCache<String, Object> cache = CacheUtil.newLRUCache(10, 10);
        // 设置监听
        cache.setListener((key, cachedObject) -> System.out.printf("key = %s,value = %s remove....\n", key, cachedObject));
        for (int i = 0; i < 20; i++) {
            cache.put("key" + i, "value" + i);
        }
    }


}
