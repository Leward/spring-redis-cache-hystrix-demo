package com.example.demoredis;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.cache.Cache;

import java.util.Optional;

public class CacheValueCommand extends HystrixCommand<String> {

    private final Cache cache;
    private final int input;

    public CacheValueCommand(Cache cache, int input) {
        super(HystrixCommandGroupKey.Factory.asKey("CacheGroup"));
        this.cache = cache;
        this.input = input;
    }

    @Override
    protected String run() throws Exception {
        return Optional.ofNullable(cache.get(input, String.class)).orElseGet(this::getFallback);
    }

    @Override
    protected String getFallback() {
        System.out.println("Using fallback");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // OK, just interrupt
        }
        return String.valueOf(input * 2);
    }
}
