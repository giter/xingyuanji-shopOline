package com.shopoline.xingyuanji.utils;


import com.sf.csim.express.service.CallExpressServiceTools;
import com.shopoline.xingyuanji.Config;
import com.shopoline.xingyuanji.Constants;
import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.entity.ProductInfo;
import com.shopoline.xingyuanji.model.RespResultModel;
import com.shopoline.xingyuanji.model.SFPrintInfoModel;
import com.shopoline.xingyuanji.model.SFResult;
import com.shopoline.xingyuanji.model.SendHomeModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class SFUtils {


    /**
     * 下单
     * @return
     */
    public static SFResult addOrder(SendHomeModel sendHomeModel, ProductInfo productInfo,SFResult sfResult) throws Exception{

        // 生成顺丰下单订单号
        sendHomeModel.setOrderId(UUID.randomUUID().toString().replaceAll("-", ""));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuffer buffer = new StringBuffer();
        buffer.append("<Request service='OrderService' lang='zh-CN' >");
        buffer.append("<Head>");
        // 顾客编码
        buffer.append(Config.Client_CODE);
        buffer.append("</Head>");
        buffer.append("<Body>");
        buffer.append("<Order ");
        buffer.append("orderid='").append(sendHomeModel.getOrderId()).append("' ");
        // 寄件信息
        buffer.append("j_company='").append(Config.J_COMPANY).append("' ");
        buffer.append("j_contact='").append(Config.J_CONTANT).append("' ");
        buffer.append("j_mobile='").append(Config.J_MOBILE).append("' ");
        buffer.append("j_province='").append(Config.J_PROVINCE).append("' ");
        buffer.append("j_city='").append(Config.J_CITY).append("' ");
        buffer.append("j_county='").append(Config.J_COUNTRY).append("' ");
        buffer.append("j_address='").append(Config.J_ADDRESS).append("' ");
        // 收货信息
        buffer.append("d_contact='").append(sendHomeModel.getName()).append("' ");
        buffer.append("d_mobile='").append(sendHomeModel.getPhone()).append("' ");
        buffer.append("d_province='").append(sendHomeModel.getProvince()).append("' ");
        buffer.append("d_city='").append(sendHomeModel.getCity()).append("' ");
        buffer.append("d_county='").append(sendHomeModel.getArea()).append("' ");
        buffer.append("d_address='").append(sendHomeModel.getAddress()).append("' ");
        buffer.append("sendstarttime='").append(sdf.format(new Date())).append("' ");
        buffer.append("parcel_quantity='1' ");
        buffer.append("is_docall ='1' ");   // 特别重要
        buffer.append("pay_method='1' ");// 付款方式
        buffer.append("routelabelService='1' ");// 路由标签查询服务默认0不查询1查询其他不查询
        // buffer.append("routelabelForReturn='1' ");// 签回单路由标签返回：默认0，1：查询，其他：不查询
        buffer.append("need_return_tracking_no='1' ");
        buffer.append("custid ='").append(Config.CUST_ID).append("' >");
        buffer.append("</Order>");
        buffer.append("</Body>");
        buffer.append("</Request>");
        String send = send(buffer.toString());
        System.out.print(send);
        JSONObject resp = JSONObject.fromObject(send);
        String code = resp.getString("Head");
        // 接口请求成功
        if (code.equals("OK")) {
            JSONObject orderResponse = resp.getJSONObject("Body").getJSONObject("OrderResponse");
            // 下单返回结果  1：人工确认  2：可收派  3：不可以收派
            String filter_result = orderResponse.getString("@filter_result");
            // 顺丰运单号
            String mailno = orderResponse.getString("@mailno");
            if (filter_result.equals("2")) {
                sfResult.setMailNo(mailno);
                sfResult.setOrderId(sendHomeModel.getOrderId());
            }
            sfResult.setMessage("快递下单成功");
            JSONObject rls_detail = resp.getJSONObject("Body").getJSONObject("OrderResponse").getJSONObject("rls_info").getJSONObject("rls_detail");
            SFPrintInfoModel sfPrintInfoModel = new SFPrintInfoModel();
            sfPrintInfoModel.setSourceCityCode(rls_detail.getString("@sourceCityCode"));
            sfPrintInfoModel.setDestCityCode(rls_detail.getString("@destCityCode"));
            sfPrintInfoModel.setDestDeptCode(rls_detail.getString("@destDeptCode"));
            //sfPrintInfoModel.setCodingMappingOut(rls_detail.getString("@codingMappingOut"));
            sfPrintInfoModel.setDestRouteLabel(rls_detail.getString("@destRouteLabel"));
            sfPrintInfoModel.setProName(rls_detail.getString("@proName"));
            sfPrintInfoModel.setCargoTypeCode(rls_detail.getString("@cargoTypeCode"));
            sfPrintInfoModel.setLimitTypeCode(rls_detail.getString("@limitTypeCode"));
            sfPrintInfoModel.setExpressTypeCode(rls_detail.getString("@expressTypeCode"));
            sfPrintInfoModel.setCodingMapping(rls_detail.getString("@expressTypeCode"));
            sfPrintInfoModel.setXbFlag(rls_detail.getString("@xbFlag"));
            sfPrintInfoModel.setTwoDimensionCode(rls_detail.getString("@twoDimensionCode"));
            sfPrintInfoModel.setProCode(rls_detail.getString("@proCode"));
            sfPrintInfoModel.setZIPAmount(sfResult.getZIPAmount());

            String fileName = SFPrintUtil.WayBillPrinterTools(sendHomeModel,mailno,productInfo,sfPrintInfoModel);
            sfResult.setOrderId(sendHomeModel.getOrderId());
            sfResult.setZIPFileName(fileName);
        }else {
            String error = resp.getString("ERROR");
            sfResult.setMessage(error);
        }
        return sfResult;
    }


    /**
     * 取消订单
     * @return
     */
    public static String exitOrder(String orderId) {

        StringBuffer buffer = new StringBuffer();

        buffer.append("<Request service='OrderConfirmService' lang='zh-CN' >");
        buffer.append("<Head>");
        buffer.append(Config.Client_CODE);
        buffer.append("</Head>");
        buffer.append("<Body>");
        buffer.append("<OrderConfirm ");
        buffer.append("orderid='").append(orderId).append("' ");
        buffer.append("dealtype='2' >");
        buffer.append("</OrderConfirm>");
        buffer.append("</Body>");
        buffer.append("</Request>");

        JSONObject resp = JSONObject.fromObject(send(buffer.toString()));

        String code = resp.getString("Head");
        // 接口请求成功
        if (code.equals("OK")) {
            JSONObject orderConfirmResponse = resp.getJSONObject("Body").getJSONObject("OrderConfirmResponse");
            // 下单返回结果  1：客户订单号与顺丰运单不匹配    2 ：操作成功
            String res_status = orderConfirmResponse.getString("@res_status");
            if (res_status.equals("2")) {
                return Constants.DING_DAN_QU_XIAO_SUCCESS;
            }
        }
        return Constants.DING_DAN_QU_XIAO_FAIL;
    }


    /**
     * 查询订单状态
     * @return
     */
    public static List<RespResultModel> getOrderStatus(String tradeNo) throws Exception {

        StringBuffer buffer = new StringBuffer();

        buffer.append("<Request service='RouteService' lang='zh-CN' >");
        buffer.append("<Head>");
        buffer.append(Config.Client_CODE);
        buffer.append("</Head>");
        buffer.append("<Body>");
        buffer.append("<RouteRequest tracking_type='1' method_type='1' tracking_number='");
        buffer.append(tradeNo).append("' /></Body></Request>");
        String send = send(buffer.toString());
        System.out.println(send);
        JSONObject resp = JSONObject.fromObject(send(buffer.toString()));

        String code = resp.getString("Head");
        // 接口请求成功
	    if (!code.equals("OK")) {
	        throw new Exception(ExceptionEnum.EXCEPTION_18.getDesc());
	    }
	    try {
            JSONArray jsonArray = resp.getJSONObject("Body").getJSONObject("RouteResponse").getJSONArray("Route");
            List<RespResultModel> resultList = new ArrayList<>();
            for(int i=0;i<jsonArray.size();i++){
                Object result = jsonArray.getJSONObject(i);
                RespResultModel resultModel = new RespResultModel();
                resultModel.setRemark(String.valueOf(((JSONObject) result).getString("@remark")));
                resultModel.setAcceptAddress(((JSONObject) result).getString("@accept_address"));
                resultModel.setAcceptTime(((JSONObject) result).getString("@accept_time"));
                resultList.add(resultModel);
            }
            return resultList;
        }catch (Exception e){
           throw new Exception(ExceptionEnum.EXCEPTION_18.getDesc());
        }
    }


    /**
     * 下订单后没有返回订单号，查询订单号
     * @return
     */
    public static String getOrderSearchServiceRequestXml(String orderId) {

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("<Request service='OrderSearchService' lang='zh-CN'>");
        strBuilder.append("<Head>" + Config.Client_CODE + "</Head>");
        strBuilder.append("<Body>");
        strBuilder.append("<OrderSearch").append(" ");
        strBuilder.append("orderid='" + orderId + "'").append(" /> ");
        strBuilder.append("</Body>");
        strBuilder.append("</Request>");
        return strBuilder.toString();
    }


    @SuppressWarnings("static-access")
    private static String send(String reqXml) {
        CallExpressServiceTools client = CallExpressServiceTools.getInstance();
        String respXml = client.callSfExpressServiceByCSIM(Config.FengQiao_ReqURL, reqXml, Config.Client_CODE, Config.CheckCode);

        XMLSerializer xmlSerializer = new XMLSerializer();
        String result = xmlSerializer.read(respXml).toString();
        return result;
    }



}
