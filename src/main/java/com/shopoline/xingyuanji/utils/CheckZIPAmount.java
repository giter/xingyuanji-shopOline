package com.shopoline.xingyuanji.utils;

import com.shopoline.xingyuanji.model.SendHomeModel;

public class CheckZIPAmount {

    public static Integer checkAmount(SendHomeModel sendHomeModel){
        Integer ZIPAmount;
        if(sendHomeModel.getProvince().equals("江苏省") && sendHomeModel.getCity().equals("苏州市")){
            ZIPAmount = 1;
        }else if(sendHomeModel.getProvince().equals("江苏省") && !sendHomeModel.getCity().equals("上海市")){
            ZIPAmount = 1;
        }else if(!sendHomeModel.getProvince().equals("江苏省") && !sendHomeModel.getCity().equals("上海市")&&!sendHomeModel.
                getProvince().equals("新疆省") &&!sendHomeModel.getProvince().equals("西藏省")){
            ZIPAmount = 1;
        }else{
            ZIPAmount = 1;
        }

        return ZIPAmount;

    }
}
