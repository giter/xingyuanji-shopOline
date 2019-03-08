package com.shopoline.xingyuanji.utils;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.shopoline.xingyuanji.Config;
import com.shopoline.xingyuanji.WxConfig;
import com.shopoline.xingyuanji.model.SendRedPackageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 发红包工具
 */
public class SendRedPackageUtil {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private WXPay wxPay = new WXPay(WxConfig.getPayInstance());

    public String sendRedPackage(SendRedPackageModel sendRedPackageModel) throws Exception{
        int capacity = (int)(14/0.75+1);
        Map<String, String> data = new HashMap<>(capacity);
        String companyName = "猩愿机";
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        // 商户订单号
        data.put("mch_billno",sendRedPackageModel.getPrizeId());
        data.put("mch_id",WxConfig.MCHID);
        data.put("wxappid", Config.APPID);
        data.put("send_name",companyName);
        data.put("re_openid",sendRedPackageModel.getOpenId());
        // 付款金额单位分
        data.put("total_amount",sendRedPackageModel.getPrice());
        // 发放人数
        data.put("total_num","1");
        data.put("wishing","恭喜你获取红包奖励");
        data.put("client_ip",getRemoteHost(sendRedPackageModel.getRequest()));
        data.put("act_name","猩愿机抽奖活动");
        // 备注
        data.put("remark","猜越多得越多，快来抢！");
        data.put("scene_id","PRODUCT_2");
        // 签名
        data.put("sign",WXPayUtil.generateSignature(data, WxConfig.SECRET, WXPayConstants.SignType.MD5));
        String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
        String respXml = wxPay.requestWithCert(url, data, 8000, 10000);
        Map<String, String> result = WXPayUtil.xmlToMap(respXml);
        String resultCode = "";
        for(Map.Entry<String,String> entity : result.entrySet()){
            if(entity.getKey().equals("result_code")){
                resultCode = entity.getValue();
            }
        }
        logger.info("<-红包返回结果->："+result+"\tDATE："+new Date());
        return resultCode;
    }


    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

}
