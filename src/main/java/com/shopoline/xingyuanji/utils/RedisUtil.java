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

    /**
     * 设置VALUE有效时间为n天
     * @param key
     * @param code
     */
    public static void setValue(String key, String code) {
        stringRedisTemplate.opsForValue().set(key, code, 1, TimeUnit.DAYS);
    }


    /**
     * 设置VALUE有效时间为n秒
     * @param key
     * @param code
     */
    public static void setValueSeconds(String key, String code) {
        stringRedisTemplate.opsForValue().set(key, code, 900, TimeUnit.SECONDS);
    }

    /**
     * 设置VALUE有效时间为n小时
     * @param key
     * @param code
     */
    public static void setValueHOURS(String key, String code) {
        stringRedisTemplate.opsForValue().set(key,code,1, TimeUnit.HOURS);
    }

    /**
     * 设置VALUE有效时间为n分钟
     * @param key
     * @param code
     */
    public static void setValueMinutes(String key, String code) {
        stringRedisTemplate.opsForValue().set(key,code,30, TimeUnit.MINUTES);
    }

    /**
     * 通过key获取VALUE
     * @param key
     * @return
     */
    public static String getValue(String key){
        return stringRedisTemplate.boundValueOps(key).get();
    }

    /**
     * 判断是否有某个KEY
     * @param key
     * @return boolean
     */
    public static Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 删除某个KEY
     * @param key
     */
    public static void delete(String key){
        stringRedisTemplate.delete(key);
    }

    /**
     * 删除key的集合
     * @param keys
     */
    public static void deleteKeys(Collection<String> keys){
        stringRedisTemplate.delete(keys);
    }

    /**
     * 写入Key集合
     * @param pattern
     * @return
     */
    public static Set<String> setKeys(String pattern){
        return stringRedisTemplate.keys(pattern);
    }
}
