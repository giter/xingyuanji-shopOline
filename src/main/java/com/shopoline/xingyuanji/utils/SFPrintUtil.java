package com.shopoline.xingyuanji.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sf.dto.CargoInfoDto;
import com.sf.dto.RlsInfoDto;
import com.sf.dto.WaybillDto;
import com.sf.util.Base64ImageTools;
import com.shopoline.xingyuanji.Config;
import com.shopoline.xingyuanji.entity.ProductInfo;
import com.shopoline.xingyuanji.model.SFPrintInfoModel;
import com.shopoline.xingyuanji.model.SendHomeModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SFPrintUtil {




    public static String WayBillPrinterTools(SendHomeModel sendHomeModel, String mailno, ProductInfo productInfo, SFPrintInfoModel sfPrintInfoModel) throws Exception{


        //根据业务需求确定请求地址
        String reqURL= "http://localhost:4040/sf/waybill/print?type=V2.0.FM_poster_100mm150mm&output=image";
        //电子面单顶部是否需要logo
        boolean topLogo=true;//true 需要logo  false 不需要logo
        if(reqURL.contains("V2.0")&&topLogo){
            reqURL=reqURL.replace("V2.0", "V2.1");
        }
        if(reqURL.contains("V3.0")&&topLogo){
            reqURL=reqURL.replace("V3.0", "V3.1");
        }

        // 注意 需要使用对应业务场景的url
        URL myURL = new URL(reqURL);
        /**
         * 其中127.0.0.1:4040为打印服务部署的地址（端口如未指定，默认为4040），
         * type为模板类型（支持两联、三联，尺寸为100mm*150mm和100mm*210mm，type为poster_100mm150mm和poster_100mm210mm）
         * A5 poster_100mm150mm   A5 poster_100mm210mm
         * output为输出类型,值为print或image，如不传，
         * 默认为print（print 表示直接打印，image表示获取图片的BASE64编码字符串）
         * V2.0/V3.0模板顶部是带logo的  V2.1/V3.1顶部不带logo
         */
        HttpURLConnection httpConn = (HttpURLConnection) myURL.openConnection();
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        httpConn.setUseCaches(false);
        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        // httpConn.setRequestProperty("Content-Type", "text/plain;charset=utf-8");
        httpConn.setConnectTimeout(5000);
        httpConn.setReadTimeout(3 * 5000);

        /**
         * 运单信息相关
         */
        List<WaybillDto> waybillDtoList = new ArrayList<WaybillDto>();
        WaybillDto dto = new WaybillDto();
        dto.setAppId(Config.Client_CODE);// 对应clientCode必填
        dto.setAppKey(Config.CheckCode);// 对应checkWord
        dto.setMailNo(mailno);
        // dto.setMailNo("755123456788,001000000002");//子母单方式
        // 签回单号  签单返回服务 会打印两份快单 其中第二份作为返寄的单
        // dto.setReturnTrackingNo("443123457778");
        // 收件人信息
        dto.setConsignerProvince(sendHomeModel.getProvince());
        dto.setConsignerCity(sendHomeModel.getCity());
        dto.setConsignerCounty(sendHomeModel.getArea());
        dto.setConsignerAddress(sendHomeModel.getAddress()); // 详细地址建议最多30个字  字段过长影响打印效果
        dto.setConsignerMobile(sendHomeModel.getPhone());
        dto.setConsignerName(sendHomeModel.getName());
        // 寄件人信息
        dto.setDeliverProvince(Config.J_PROVINCE);
        dto.setDeliverCity(Config.J_CITY);
        dto.setDeliverCounty(Config.J_COUNTRY);
        dto.setDeliverCompany(Config.J_CONTANT);
        dto.setDeliverAddress(Config.J_ADDRESS);// 详细地址建议最多30个字  字段过长影响打印效果
        dto.setDeliverName("猩愿机");
        dto.setDeliverShipperCode("215000");
        dto.setDeliverTel(Config.J_MOBILE);
        dto.setDestCode(sfPrintInfoModel.getDestCityCode());// 目的地代码 参考顺丰地区编号
        dto.setZipCode("512");// 原寄地代码 参考顺丰地区编号
        // 陆运E标示,业务类型为“电商特惠、顺丰特惠、电商专配、陆运件”则必须打印E标识，用以提示中转场分拣为陆运
        dto.setElectric("E");
        //快递类型 1 ：标准快递   2.顺丰特惠    6：顺丰即日  7.电商速配   15：生鲜速配
        dto.setExpressType(2);
        // COD代收货款金额,只需填金额, 单位元- 此项和月结卡号绑定的增值服务相关
        //dto.setCodValue(String.valueOf(sfPrintInfoModel.getZIPAmount()));
        dto.setInsureValue("0");// 声明货物价值的保价金额,只需填金额,单位元
        dto.setMonthAccount(Config.CUST_ID);// 月结卡号
        dto.setPayMethod(1);//

        /**
         * 丰密运单相关-如非使用丰密运单模板 不需要设置以下值
         */
        List<RlsInfoDto> rlsInfoDtoList =new  ArrayList<RlsInfoDto>();
        RlsInfoDto rlsMain=new  RlsInfoDto();
        // 主运单号
        rlsMain.setWaybillNo(dto.getMailNo());
        rlsMain.setDestRouteLabel(sfPrintInfoModel.getDestRouteLabel());
        rlsMain.setPrintIcon("00010000");
        rlsMain.setProCode(sfPrintInfoModel.getProCode());
        // rlsMain.setAbFlag(s);
        rlsMain.setXbFlag(sfPrintInfoModel.getXbFlag());
        rlsMain.setCodingMapping(sfPrintInfoModel.getCodingMapping());
        // rlsMain.setCodingMappingOut(sfPrintInfoModel.getCodingMappingOut());
        // rlsMain.setDestTeamCode(sfPrintInfoModel.getDestTeamCode());
        rlsMain.setSourceTransferCode(sfPrintInfoModel.getSourceCityCode());
        // 对应下订单设置路由标签返回字段twoDimensionCode 该参
        rlsMain.setQrcode(sfPrintInfoModel.getTwoDimensionCode());
        rlsInfoDtoList.add(rlsMain);


//        if(dto.getReturnTrackingNo()!=null){
//            RlsInfoDto rlsBack=new  RlsInfoDto();
//            //签回运单号Z
//            rlsBack.setWaybillNo(dto.getReturnTrackingNo());
//            rlsBack.setDestRouteLabel("021WTF");
//            rlsBack.setPrintIcon("11110000");
//            rlsBack.setProCode("T4");
//            rlsBack.setAbFlag("A");
//            rlsBack.setXbFlag("XB");
//            rlsBack.setCodingMapping("1A");
//            rlsBack.setCodingMappingOut("F33");
//            rlsBack.setDestTeamCode("87654321");
//            rlsBack.setSourceTransferCode("755WE-571A3");
//            //对应下订单设置路由标签返回字段twoDimensionCode 该参
//            rlsBack.setQrcode("MMM={'k1':'21WT','k2':'755WE','k3':'','k4':'T4','k5':'443123456789','k6':''}");
//            rlsInfoDtoList.add(rlsBack);
//        }

        // 设置丰密运单必要参数
        dto.setRlsInfoDtoList(rlsInfoDtoList);
        // 客户个性化Logo 必须是个可以访问的图片本地路径地址或者互联网图片地址
        dto.setCustLogo("C:\\LOGO.png");
        /**
         * 备注相关
         */
//        dto.setMainRemark("社区送基情");
//        dto.setChildRemark("子单号备注");
//        dto.setReturnTrackingRemark("迁回单备注");
        /**
         * 加密项
         */
        dto.setEncryptCustName(false);// 加密寄件人及收件人名称
        dto.setEncryptMobile(false);// 加密寄件人及收件人联系手机
        /**
         * 物品相关
         */
        CargoInfoDto cargo1 = new CargoInfoDto();
        cargo1.setCargo(productInfo.getGoodsname());
        cargo1.setCargoCount(1);
        cargo1.setCargoUnit("件");
        cargo1.setSku("00015645");
        cargo1.setRemark("猩愿机送惊喜");
        List<CargoInfoDto> cargoInfoList = new ArrayList<CargoInfoDto>();
        cargoInfoList.add(cargo1);
        dto.setCargoInfoDtoList(cargoInfoList);

        waybillDtoList.add(dto);

        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter,waybillDtoList);

        httpConn.getOutputStream().write(stringWriter.toString().getBytes("UTF-8"));

        httpConn.getOutputStream().flush();
        httpConn.getOutputStream().close();
        InputStream in = httpConn.getInputStream();

        BufferedReader in2=new BufferedReader(new InputStreamReader(in));

        String y="";

        String strImg="";
        while((y=in2.readLine())!=null){

            strImg=y.substring(y.indexOf("[")+1,y.length()-"]".length()-1);
            if(strImg.startsWith("\"")){
                strImg=strImg.substring(1,strImg.length());
            }
            if(strImg.endsWith("\"")){
                strImg=strImg.substring(0,strImg.length()-1);
            }

        }

        // 将换行全部替换成空
        strImg=strImg.replace("\\n", "");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String dateStr = format.format(new Date());

        List<String> files = new ArrayList<String>();
        String fileName = null;
        if(strImg.contains("\",\"")){
            // 如子母单及签回单需要打印两份或者以上
            String[] arr = strImg.split("\",\"");
            /**
             * 输出图片到本地 支持.jpg、.png格式
             */
            for(int i = 0; i < arr.length; i++) {
                fileName = "C:\\SF_ORDER"+dateStr+"-"+i+".jpg";
                Base64ImageTools.generateImage(arr[i], fileName);
                files.add(fileName);
            }
        }else{
            fileName = "C:\\SF_ORDER"+dateStr+productInfo.getGoodsname()+".jpg";
            Base64ImageTools.generateImage(strImg, fileName);
            files.add(fileName);
        }

        /**
         * 调用本地打印机
         */
//		int high = 0;
//		if(reqURL.contains("image") && !files.isEmpty()) {
//			if (reqURL.contains("V2")) {
//				high = 150;
//			}else {
//				high = 210;
//			}
//			for (String fileName : files) {
//				PrintUtil.drawImage(fileName,high,false);// false为不弹出打印框
//			}
//		}
        return fileName;
    }


}
