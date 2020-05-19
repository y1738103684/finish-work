package com.nuc.finish.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.nuc.finish.service.CacheService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/14 8:52
 */
@Service
public class CacheServiceImpl implements CacheService {

    private Cache<String, Object> cache = null;

    @PostConstruct
    public void init() {
        cache = CacheBuilder.newBuilder()
                //初始化本是缓存的空间
                .initialCapacity(10)
                //设置缓存中最大可以存储多少个值
                .maximumSize(100)
                //设置key过期时间
                .expireAfterWrite(60, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public void setCache(String key, Object value) {
        cache.put(key, value);
    }

    @Override
    public Object getFromCache(String key) {
        return cache.getIfPresent(key);
    }
}
