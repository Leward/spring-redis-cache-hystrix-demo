package com.example.demoredis;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheValueCommandFactory {

    private final Cache cache;

    public CacheValueCommandFactory(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("test_get_some");
    }

    public CacheValueCommand newCommand(int i) {
        return new CacheValueCommand(cache, i);
    }

}
