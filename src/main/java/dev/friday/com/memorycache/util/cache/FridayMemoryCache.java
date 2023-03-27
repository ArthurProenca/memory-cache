package dev.friday.com.memorycache.util.cache;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/*
    @author: Friday
    @date: 2020/5/14
    @description:
 */
public class FridayMemoryCache<K, V> {

    private final Map<K, FridayCacheObject<V>> cache = new HashMap<>();
    private final long timeout;
    private static final Logger log = Logger.getLogger(FridayMemoryCache.class.getName());

    public FridayMemoryCache(long timeout) {
        this.timeout = timeout;
    }

    public synchronized void put(K key, V value) {
        log.info("put key: " + key + " value: " + value + " on cache");
        cache.put(key, new FridayCacheObject<V>(value, System.currentTimeMillis() + timeout));
    }

    public synchronized V get(K key) {
        FridayCacheObject<V> cacheObject = cache.get(key);
        if (cacheObject == null) {
            log.info("The " + key.toString() + " is not in cache");
            return null;
        } else {
            if (cacheObject.isExpired()) {
                cache.remove(key);
                log.info("The " + key.toString() + " is expired");
                return null;
            } else {
                log.info("get key: " + key + " value: " + cacheObject + " from cache");
                return cacheObject.value();
            }
        }
    }


    private record FridayCacheObject<T>(T value, long expiryTime) {

        private Boolean isExpired() {
            return System.currentTimeMillis() >= this.expiryTime;
        }
    }
}
