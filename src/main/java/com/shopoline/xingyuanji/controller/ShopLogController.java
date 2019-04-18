package com.shopoline.xingyuanji.controller;

import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.model.PayModel;
import com.shopoline.xingyuanji.model.ShopLogModel;
import com.shopoline.xingyuanji.service.db1.IShopLogService;
import com.shopoline.xingyuanji.service.db1.WxPayService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.AfterPaySuccessVO;
import com.shopoline.xingyuanji.vo.LogisticInformationVO;
import com.shopoline.xingyuanji.vo.ShopLogInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
 * @since 2019-01-10
 */
@Api(description = "购物记录接口")
@Controller
@Scope("prototype")
@RequestMapping("/shopLog")
public class ShopLogController extends BaseController {

    @Autowired
    private IShopLogService shopLogService;
    @Autowired
    private WxPayService wxPayService;

    /**
     * 支付
     * @param ticketId
     * @param payModel
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "支付" ,  notes="支付")
    @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String")
    @ResponseBody
    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    public Object WXPay(String ticketId,@RequestBody PayModel payModel, HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult();
        try {
            json.setData(shopLogService.setOrder(ticketId,payModel));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 扣除猩币
     * @param ticketId
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "扣除猩币" ,  notes="扣除猩币")
    @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String")
    @ResponseBody
    @RequestMapping(value = "/deductXingBi",method = RequestMethod.POST)
    public Object deductXingBi(String ticketId,HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            shopLogService.deductXingBi(ticketId);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 支付结果异步通知
     *
     * @param string
     * @return
     */
    @ApiOperation(value = "支付结果异步通知" ,  notes="支付结果异步通知")
    @ApiImplicitParam(name = "string" ,value ="string",required = true, dataType = "String")
    @ResponseBody
    @RequestMapping(value = "/notify",method = RequestMethod.POST)
    public Object payNotify(String string){
        JsonResult<Object> json = new JsonResult<>();
        try {
            json.setData(wxPayService.payNotify(string));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 交易成功后业务
     * @param ticketId
     * @return
     */
    @ApiOperation(value = "扣除猩币" ,  notes="扣除猩币")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String"),
            @ApiImplicitParam(name = "useXingBi" ,value ="是否使用猩币 使用传1",required = true, dataType = "String"),
            @ApiImplicitParam(name = "isPay" ,value ="是否支付 支付传1",required = true, dataType = "String"),
            @ApiImplicitParam(name = "randomToken" ,value ="随机TOKEN，支付成功后回传前端，返回后端校验REDIS中数据，如果没有相关数据前端页面跳转个人中心",required = true, dataType = "String"),
            @ApiImplicitParam(name = "UUID" ,value ="UUID，支付成功后回传前端，返回后端校验REDIS中数据，如果没有相关数据前端页面跳转个人中心",required = true, dataType = "String"),
    })
    @ResponseBody
    @RequestMapping(value = "/getOpenRandomGoods",method = RequestMethod.POST)
    public Object getOpenRandomGoods(String ticketId,String useXingBi,String isPay,String randomToken,String UUID,HttpServletRequest request, HttpServletResponse response){
        JsonResult<AfterPaySuccessVO> json = new JsonResult<>();
        try {
            json.setData(shopLogService.afterPaySuccess(ticketId,useXingBi,isPay,randomToken,UUID));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }


    /**
     * 查看订单记录基本信息
     * @return
     */
    @ApiOperation(value = "查看订单记录基本信息" ,  notes="查看订单记录基本信息")
    @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String")
    @ResponseBody
    @RequestMapping(value = "/getShopLog",method = RequestMethod.POST)
    public Object getShopLog(String ticketId,HttpServletRequest request, HttpServletResponse response){
        JsonResult<List<ShopLogModel>> json = new JsonResult<>();
        try {
            json.setData(shopLogService.getShopLog(ticketId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }


    /**
     * 查看订单记录商品详情
     * @return
     */
    @ApiOperation(value = "查看订单记录商品详情" ,  notes="查看订单记录商品详情")
    @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String")
    @ResponseBody
    @RequestMapping(value = "/getShopLogInfo",method = RequestMethod.POST)
    public Object getShopLogInfo(String ticketId,HttpServletRequest request, HttpServletResponse response){
        JsonResult<ShopLogInfoVO> json = new JsonResult<>();
        try {
            json.setData(shopLogService.getShopLogInfo(ticketId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 获取运费
     */
    @ApiOperation(value = "获取运费" ,  notes="获取运费")
    @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String")
    @ResponseBody
    @RequestMapping(value = "/getZIPAmount",method = RequestMethod.POST)
    public Object getZIPAmount(String ticketId, HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            json.setData(shopLogService.getZIPAmount(ticketId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 邮回家
     */
    @ApiOperation(value = "邮回家" ,  notes="邮回家")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String"),
            @ApiImplicitParam(name = "productId" ,value ="商品Id",required = true, dataType = "String"),
            @ApiImplicitParam(name = "shopLogId" ,value ="购买记录Id",required = true, dataType = "String"),
            })
    @ResponseBody
    @RequestMapping(value = "/sendHome",method = RequestMethod.POST)
    public Object sendHome(String ticketId,String productId,String shopLogId,HttpServletRequest request, HttpServletResponse response){
        JsonResult json = new JsonResult();
        try {
            shopLogService.sendHome(ticketId,productId,shopLogId);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 取消订单
     */
    @ApiOperation(value = "取消订单" ,  notes="取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticketId", value = "ticketId", required = true, dataType = "String"),
            @ApiImplicitParam(name = "orderId", value = "付款订单号", required = true, dataType = "String")
    })
    @ResponseBody
    @RequestMapping(value = "/deleteOrder",method = RequestMethod.POST)
    public Object deleteOrder(String ticketId,String orderId,HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            json.setData(shopLogService.deleteOrder(ticketId,orderId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }


    /**
     * 购买积分商品
     */
    @ApiOperation(value = "购买积分商品" ,  notes="购买积分商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticketId", value = "ticketId", required = true, dataType = "String"),
            @ApiImplicitParam(name = "productId", value ="商品Id", required = true, dataType = "String")
    })
    @ResponseBody
    @RequestMapping(value = "/buyXingBiProduct",method = RequestMethod.POST)
    public Object buyXingBiProduct(String ticketId,String productId,HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            json.setData(shopLogService.buyXingBiProduct(ticketId,productId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }


    /**
     * 获取物流信息
     * @param ticketId
     * @param productId
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "获取物流信息" ,  notes="获取物流信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticketId", value = "ticketId", required = true, dataType = "String"),
            @ApiImplicitParam(name = "productId", value ="商品Id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "tradeNo", value ="运单号", required = true, dataType = "String")
    })
    @ResponseBody
    @RequestMapping(value = "/getLogisticInformation",method = RequestMethod.POST)
    public Object getLogisticInformation(String ticketId,String productId,String tradeNo,HttpServletRequest request, HttpServletResponse response){
        JsonResult<LogisticInformationVO> json = new JsonResult<>();
        try {
            json.setData(shopLogService.getLogisticInformation(ticketId,productId,tradeNo));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

}

