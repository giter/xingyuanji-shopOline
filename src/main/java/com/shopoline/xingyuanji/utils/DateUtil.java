package com.shopoline.xingyuanji.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String FormatDate(Date date){
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss");
        String format = bf.format(date);
        return format;
    }

}
