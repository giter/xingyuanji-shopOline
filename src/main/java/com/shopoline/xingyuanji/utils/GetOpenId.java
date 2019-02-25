package com.shopoline.xingyuanji.utils;

public class GetOpenId {

    public static String getOpenId(String ticketId){
        String openId = RedisUtil.getValue(ticketId);
        return openId;
    }
}
