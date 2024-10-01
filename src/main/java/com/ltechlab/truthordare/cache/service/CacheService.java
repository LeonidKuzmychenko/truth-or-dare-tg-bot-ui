package com.ltechlab.truthordare.cache.service;

import com.google.common.cache.Cache;
import com.ltechlab.truthordare.cache.dto.CacheData;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CacheService {

    private final Cache<String, CacheData> cache;

    public CacheService(Cache<String, CacheData> cache) {
        this.cache = cache;
    }

    public CacheData get(String chatId){
        try {
            return cache.get(chatId, CacheData::new);
        }catch (ExecutionException e){
            e.printStackTrace();
            return new CacheData();
        }
    }

    public CacheData put(String chatId, CacheData cacheData){
        cache.put(chatId, cacheData);
        return cacheData;
    }

}