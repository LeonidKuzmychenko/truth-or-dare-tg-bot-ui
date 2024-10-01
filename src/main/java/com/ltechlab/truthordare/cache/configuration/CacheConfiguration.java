package com.ltechlab.truthordare.cache.configuration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.ltechlab.truthordare.cache.dto.CacheData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfiguration {

    @Bean("cacheData")
    public Cache<String, CacheData> cacheData() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build();
    }


}