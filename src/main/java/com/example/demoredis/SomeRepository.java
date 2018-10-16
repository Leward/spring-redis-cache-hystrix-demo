package com.example.demoredis;

import org.springframework.stereotype.Component;

@Component
public class SomeRepository {

    private final CacheValueCommandFactory cacheValueCommandFactory;

    public SomeRepository(CacheValueCommandFactory cacheValueCommandFactory) {
        this.cacheValueCommandFactory = cacheValueCommandFactory;
    }


    public String get(int i) {
        return cacheValueCommandFactory.newCommand(i).execute();
    }

}
