package com.nuc.finish.service;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/5/14 8:49
 */
public interface CacheService {
    void setCache(String key, Object value);
    Object getFromCache(String key);
}
