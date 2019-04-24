package com.shopoline.xingyuanji.controller;


import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.entity.ProductInfo;
import com.shopoline.xingyuanji.service.db1.IProductInfoService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.ProductInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Api(description = "商品详情接口")
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
    @ApiOperation(value = "获取盒子信息" ,  notes="获取盒子信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = false, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "type" ,value ="商品类型，获取盒子传0",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "kind" ,value ="商品分类，盒子传2",required = true, dataType = "String",paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/getBoxInfo",method = RequestMethod.POST)
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
    @ApiOperation(value = "获取盒子抵扣价格" ,  notes="获取盒子抵扣价格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "type" ,value ="商品类型 0：盒子商品  1：积分商品 ",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "kind" ,value ="商品分类：0：开盒商品1：积分兑换商品2，盒子",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "boxCount" ,value ="购买盒子数量",required = true, dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/getBoxDeductionPrice",method = RequestMethod.POST)
    public Object getBoxDeductionPrice(String ticketId,String type,String kind,Integer boxCount,HttpServletRequest request,HttpServletResponse response){
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
    @ApiOperation(value = "获取积分商城商品列表" ,  notes="获取积分商城商品列表")
    @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = false, dataType = "String")
    @ResponseBody
    @RequestMapping(value = "/getShopList",method = RequestMethod.POST)
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
    @ApiOperation(value = "获取积分商城商品列表" ,  notes="获取积分商城商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String"),
            @ApiImplicitParam(name = "productId" ,value ="商品Id",required = true, dataType = "String",paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/getShopProductInfo",method = RequestMethod.POST)
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
    @ApiOperation(value = "获取积分商城商品列表" ,  notes="获取积分商城商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boxId" ,value ="盒子id，盒子Id为0",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String",paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/getBoxImg",method = RequestMethod.POST)
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

    @ApiOperation(value = "更改数据库用" ,  notes="更改数据库用")
    @ResponseBody
    @PutMapping(value = "/setImg")
    public Object setImg(HttpServletRequest request,HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            productInfoService.setImg();
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }
}

