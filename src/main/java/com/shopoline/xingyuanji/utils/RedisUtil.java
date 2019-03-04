package com.shopoline.xingyuanji.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtil {

    /**
     * 1.StringRedisTemplate继承了RedisTemplate。
     * 2.RedisTemplate是一个泛型类，而StringRedisTemplate则不是。
     * 3.StringRedisTemplate只能对key=String，value=String的键值对进行操作，RedisTemplate可以对任何类型的key-value键值对操作。
     * 4.他们各自序列化的方式不同，但最终都是得到了一个字节数组，殊途同归，StringRedisTemplate使用的是StringRedisSerializer类；
     * 5.RedisTemplate使用的是JdkSerializationRedisSerializer类。反序列化，则是一个得到String，一个得到Object
     */
    private static StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringUtil.getBean("stringRedisTemplate");

    public static void setValue(String key, String code) {
        stringRedisTemplate.opsForValue().set(key, code, 1, TimeUnit.DAYS);
    }

    public static void setValueSeconds(String key, String code) {
        stringRedisTemplate.opsForValue().set(key, code, 900, TimeUnit.SECONDS);
    }

    public static void setValueHOURS(String key, String code) {
        stringRedisTemplate.opsForValue().set(key,code,1, TimeUnit.HOURS);
    }

    public static void setValueMinutes(String key, String code) {
        stringRedisTemplate.opsForValue().set(key,code,30, TimeUnit.MINUTES);
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
