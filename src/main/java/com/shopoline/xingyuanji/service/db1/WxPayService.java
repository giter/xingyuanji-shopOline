package com.shopoline.xingyuanji.service.db1;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * 微信支付接口
 */
public interface WxPayService {

    /**
     * 微信统一下单
     * @param map
     * @return
     */
    JSONObject unifiedorder(HashMap<String,String> map);

    /**
     * 支付异步通知
     * @param string
     * @return
     */
    Object payNotify(String string);
}
