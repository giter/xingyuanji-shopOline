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
        return str.replace("-", "");
    }
}
