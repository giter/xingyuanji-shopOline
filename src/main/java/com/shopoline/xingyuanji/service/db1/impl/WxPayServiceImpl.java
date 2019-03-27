package com.shopoline.xingyuanji.service.db1.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.shopoline.xingyuanji.WxConfig;
import com.shopoline.xingyuanji.service.db1.WxPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 微信支付相关实现
 */
@Service
public class WxPayServiceImpl implements WxPayService {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private WXPay wxPay = new WXPay(WxConfig.getPayInstance());

    /**
     * 微信统一下单
     * @return
     */
    public JSONObject unifiedorder(ConcurrentHashMap<String, String> map){

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", 0);
            try {
                String tradeType = map.get("trade_type");
                // 微信sdk，统一下单接口
                Map<String, String> resultMap = wxPay.unifiedOrder(map);
                if (!"SUCCESS".equals(resultMap.get("result_code"))) {
                    jsonObject.put("object", resultMap);
                    jsonObject.put("msg", "统一下单失败");
                    logger.error("<-WXPAY_RESULT->：" + jsonObject.get("object") + "\tResult：统一下单失败");
                    return jsonObject;
                }
                if (!WXPayUtil.isSignatureValid(resultMap, WxConfig.SECRET)) {
                    jsonObject.put("msg", "验证签名失败");
                    logger.error("<-WXPAY_RESULT->：" + "\tResult：验证签名失败");
                    return jsonObject;
                }
                jsonObject.put("result", 0);
                if ("JSAPI".equals(tradeType)) {
                    int capacity = (int) (5 / 0.75) + 1;
                    // 小程序，微信公众号
                    ConcurrentHashMap<String, String> payInfoMap = new ConcurrentHashMap<>(capacity);
                    payInfoMap.put("appId", resultMap.get("appid"));
                    payInfoMap.put("nonceStr", resultMap.get("nonce_str"));
                    payInfoMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");
                    payInfoMap.put("package", "prepay_id=" + resultMap.get("prepay_id"));
                    payInfoMap.put("signType", "MD5");
                    String sign = WXPayUtil.generateSignature(payInfoMap, WxConfig.SECRET);
                    payInfoMap.put("paySign", sign);
                    jsonObject.put("object", payInfoMap);
                    logger.info("<-JSAPI_WXPAY_RESULT->：" + payInfoMap);
                }
                if ("NATIVE".equals(tradeType)) {//扫码
                    jsonObject.put("url", resultMap.get("code_url"));
                }
                if ("APP".equals(tradeType)) {//app
                    jsonObject.put("object", resultMap);
                }
                if ("MWEB".equals(tradeType)) {//H5
                    jsonObject.put("url", resultMap.get("mweb_url"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return jsonObject;
    }

    /**
     * 支付异步通知
     * @param string
     * @return
     */
    @Override
    public Object payNotify(String string) {
        try {
            Map<String,String> map = WXPayUtil.xmlToMap(string);
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map));
            if (WXPayUtil.isSignatureValid(map, WxConfig.SECRET)) {
                System.out.println("系统验证签名正确");
            }
            System.out.println("商户订单号"+jsonObject.getString("out_trade_no"));
            System.out.println("支付完成时间"+jsonObject.getString("time_end"));
            System.out.println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }


}
