package dev.friday.com.memorycache.service;

import dev.friday.com.memorycache.util.cache.FridayMemoryCache;
import dev.friday.com.memorycache.client.ZipCodeFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ZipCodeRestService implements InitializingBean {

    private final ZipCodeFeignClient zipCodeFeignClient;

    private static final long cacheTimeout = TimeUnit.MINUTES.toMillis(1);

    private FridayMemoryCache<String, Object> fridayMemoryCache;

    public Object getZipCodeInfo(String zipCode) {
        Object cachedObject = fridayMemoryCache.get(zipCode);

        if(cachedObject != null) {
            return cachedObject;
        } else {
            Object zipCodeInfo = zipCodeFeignClient.getZipCodeInfo(zipCode);
            fridayMemoryCache.put(zipCode, zipCodeInfo);
            return zipCodeInfo;
        }
    }

    @Override
    public void afterPropertiesSet() {
        this.fridayMemoryCache = new FridayMemoryCache<>(cacheTimeout);
    }
}
