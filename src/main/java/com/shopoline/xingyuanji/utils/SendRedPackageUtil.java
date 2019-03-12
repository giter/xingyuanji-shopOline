package com.shopoline.xingyuanji.utils;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.shopoline.xingyuanji.Config;
import com.shopoline.xingyuanji.WxConfig;
import com.shopoline.xingyuanji.model.SendRedPackageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 发红包工具
 */
public class SendRedPackageUtil {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private WXPay wxPay = new WXPay(WxConfig.getPayInstance());

    public String sendRedPackage(SendRedPackageModel sendRedPackageModel) throws Exception{

        int capacity = (int)(14/0.75+1);
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(capacity);
        map.put("nonce_str", WXPayUtil.generateNonceStr());
        // 商户订单号
        map.put("mch_billno",sendRedPackageModel.getPrizeId()+ TicketUtil.get5_RandomNum());
        map.put("mch_id",WxConfig.MCHID);
        map.put("wxappid", Config.APPID);
        map.put("send_name","猩愿机");
        map.put("re_openid",sendRedPackageModel.getOpenId());
        // 付款金额单位分
        map.put("total_amount",sendRedPackageModel.getPrice());
        // 发放人数
        map.put("total_num","1");
        map.put("wishing","恭喜你获取红包奖励");
        map.put("client_ip",IPUtils.getRemoteHost(sendRedPackageModel.getRequest()));
        map.put("act_name","猩愿机抽奖活动");
        // 备注
        map.put("remark","猜越多得越多，快来抢！");
        map.put("scene_id","PRODUCT_2");
        // 签名
        map.put("sign",WXPayUtil.generateSignature(map, WxConfig.SECRET, WXPayConstants.SignType.MD5));
        String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
        String respXml = wxPay.requestWithCert(url, map, 8000, 10000);
        Map<String, String> result = WXPayUtil.xmlToMap(respXml);
        String resultCode = "";
        for(Map.Entry<String,String> entry : result.entrySet()){
            if(entry.getKey().equals("result_code")){
                resultCode = entry.getValue();
            }
        }
        logger.info("<-红包返回结果->："+result+"\tDATE："+new Date());
        return resultCode;
    }




}
