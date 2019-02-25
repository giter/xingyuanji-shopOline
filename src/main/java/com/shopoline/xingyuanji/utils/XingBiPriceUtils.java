package com.shopoline.xingyuanji.utils;

public class XingBiPriceUtils {

    public static Integer getBuyBoxPrice(Integer count){
        return count*10;
    }

    public static Integer getUseRmbPrice(Integer amount){
        return amount *10;
    }


}
