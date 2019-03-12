package com.shopoline.xingyuanji.service.db1.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.shopoline.xingyuanji.Config;
import com.shopoline.xingyuanji.WxConfig;
import com.shopoline.xingyuanji.service.db1.WxPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


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
    public JSONObject unifiedorder(Map<String,String> data){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",0);
        try {
            String tradeType = data.get("trade_type");
            // 微信sdk，统一下单接口
            Map<String, String> result = wxPay.unifiedOrder(data);
            if (!"SUCCESS".equals(result.get("result_code"))){
                jsonObject.put("object",result);
                jsonObject.put("msg","统一下单失败");
                logger.error("<-WXPAY_RESULT->："+jsonObject.get("object")+"\tResult：统一下单失败");
                return jsonObject;
            }
            if (!WXPayUtil.isSignatureValid(result, WxConfig.SECRET)){
                jsonObject.put("msg","验证签名失败");
                logger.error("<-WXPAY_RESULT->："+"\tResult：验证签名失败");
                return jsonObject;
            }
            jsonObject.put("result",0);
            if ("JSAPI".equals(tradeType)){
                int capacity = (int)(5/0.75)+1;
                // 小程序，微信公众号
                HashMap<String,String> payInfo = new HashMap<>(capacity);
                payInfo.put("appId", Config.APPID);
                payInfo.put("nonceStr",result.get("nonce_str"));
                payInfo.put("timeStamp",System.currentTimeMillis()/1000+"");
                payInfo.put("package","prepay_id="+result.get("prepay_id"));
                payInfo.put("signType","MD5");
                String sign = WXPayUtil.generateSignature(payInfo, WxConfig.SECRET);
                payInfo.put("paySign",sign);
                jsonObject.put("object",payInfo);
                logger.info("<-JSAPI_WXPAY_RESULT->："+ payInfo);
            }
            if ("NATIVE".equals(tradeType)){//扫码
                jsonObject.put("url",result.get("code_url"));
            }
            if ("APP".equals(tradeType)){//app
                jsonObject.put("object",result);
            }
            if ("MWEB".equals(tradeType)){//H5
                jsonObject.put("url",result.get("mweb_url"));
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
            Map map = WXPayUtil.xmlToMap(string);
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map));
            if (WXPayUtil.isSignatureValid(map, WxConfig.SECRET)) {
                System.out.println("系统验证签名正确");
            }
//            String tradeNo = jsonObject.getString("out_trade_no");
            System.out.println("商户订单号"+jsonObject.getString("out_trade_no"));
            System.out.println("支付完成时间"+jsonObject.getString("time_end"));
            System.out.println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }


}
