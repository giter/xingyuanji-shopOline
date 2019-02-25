package com.shopoline.xingyuanji.utils;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtil {

    private static StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringUtil.getBean("stringRedisTemplate");

    public static void setValue(String key, String code) {
        stringRedisTemplate.opsForValue().set(key, code, 1, TimeUnit.DAYS);
    }

    public static void setValueSeconds(String key, String code) {
        stringRedisTemplate.opsForValue().set(key, code, 900, TimeUnit.SECONDS);
    }

    public static void setValueHOURS(String key, String code) {
        stringRedisTemplate.opsForValue().set(key, code, 1, TimeUnit.HOURS);
    }

    public static String getValue(String key){
        return stringRedisTemplate.boundValueOps(key).get();
    }

    public static Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    public static void delete(String key){
        stringRedisTemplate.delete(key);
    }

    public static void deleteKeys(Collection<String> keys){
        stringRedisTemplate.delete(keys);
    }

    public static Set<String> setKeys(String pattern){
        return stringRedisTemplate.keys(pattern);
    }
}
