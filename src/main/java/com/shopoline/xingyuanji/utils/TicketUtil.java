package com.shopoline.xingyuanji.utils;

import java.util.UUID;

public class TicketUtil {

    public static String getTicketId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String getUserIdByTicket(String ticketId){
        return RedisUtil.getValue(ticketId);
    }

    public static String get32_UUID() {
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        // bb01a3cd-987f-469d-9e0f-04dffa2d3278
        System.out.println("str: " + str);
        // bb01a3cd987f469d9e0f04dffa2d3278
        return str.replace("-", "");
    }
}
