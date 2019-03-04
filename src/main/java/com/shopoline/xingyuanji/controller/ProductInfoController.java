package com.shopoline.xingyuanji.controller;


import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.entity.ProductInfo;
import com.shopoline.xingyuanji.service.IProductInfoService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.ProductInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author wuty
 * @since 2019-01-09
 */
@Controller
@Scope("prototype")
@RequestMapping("/productInfo")
public class ProductInfoController extends BaseController {

    @Autowired
    private IProductInfoService productInfoService;

    /**
     * 获取盒子信息
     * @param ticketId
     * @param type
     * @param kind
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBoxInfo")
    public Object getBoxInfo(String ticketId,String type,String kind,HttpServletRequest request,HttpServletResponse response){
        JsonResult<ProductInfoVO> json = new JsonResult<>();
        try {
            json.setData(productInfoService.getBoxInfo(type,kind));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 获取盒子抵扣价格
     * @param ticketId
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBoxDeductionPrice")
    public Object getBoxDeductionPrice(String ticketId,String type,String kind,Integer boxCount,String boxId,HttpServletRequest request,HttpServletResponse response){
        JsonResult<Float> json = new JsonResult<>();
        try {
            json.setData(productInfoService.getBoxDeductionPrice(ticketId,type,kind,boxCount));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }


    /**
     * 获取积分商城商品列表
     * @param ticketId
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getShopList")
    public Object getShopList(String ticketId,HttpServletRequest request,HttpServletResponse response){
        JsonResult<List<ProductInfo>> json  = new JsonResult<>();
        try {
            json.setData(productInfoService.getShopList());
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }


    /**
     * 获取商城商品详情
     * @param ticketId
     * @param productId
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getShopProductInfo")
    public Object getShopProductInfo(String ticketId,String productId,HttpServletRequest request,HttpServletResponse response){
        JsonResult<ProductInfo> json = new JsonResult<>();
        try {
            json.setData(productInfoService.getShopProductInfo(productId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 获取盒子对应图片
     * @param ticketId
     * @param boxId
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBoxImg")
    public Object getBoxImg(String ticketId,String boxId,HttpServletRequest request,HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            json.setData(productInfoService.getBoxImg(boxId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }
}

