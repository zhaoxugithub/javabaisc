package com.serendipity.cache;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CacheUtil {

    private static final Logger log = LoggerFactory.getLogger(CacheUtil.class);

    @Data
    static class MyCache {
        private String key;
        private Object value;
        private Long expireTime;
    }

    /**
     * 缓存数据Map
     */
    private static final Map<String, MyCache> cache = new ConcurrentHashMap<>();
    /**
     * 定时器线程池,用来定时清除过期缓存
     */
    private static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    static {
        // 注册一个定时线程任务，服务启动1s之后,每隔500ms 执行一次
        // 定时清理过期缓存
        executorService.scheduleAtFixedRate(CacheUtil::clearCache, 1000, 500, TimeUnit.MILLISECONDS);
    }

    public static void put(String key, Object value, long expire) {
        MyCache myCache = new MyCache();
        myCache.setKey(key);
        myCache.setValue(value);
        if (expire > 0) {
            long expireTime = System.currentTimeMillis() + expire;
            myCache.setExpireTime(expireTime);
        }
        cache.put(key, myCache);
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        if (cache.containsKey(key)) {
            return cache.get(key).getValue();
        }
        return null;
    }

    /**
     * 移除缓存
     *
     * @param key
     */
    public static void remove(String key) {
        cache.remove(key);
    }

    /**
     * 清理过期的缓存数据
     */
    private static void clearCache() {
        if (cache.isEmpty()) {
            log.info("cache is empty");
            return;
        }
        cache.entrySet()
                .removeIf(entry -> entry.getValue().getExpireTime() != null
                && entry.getValue().getExpireTime() < System.currentTimeMillis());
    }

    public static void main(String[] args) throws InterruptedException {
        CacheUtil.put("name", "zhangsan", 1000);
        Object name = CacheUtil.get("name");
        System.out.println("first get name = " + name);
        TimeUnit.SECONDS.sleep(3);
        Object name1 = CacheUtil.get("name");
        System.out.println("second get name = " + name1);
        executorService.shutdown();
    }
}
