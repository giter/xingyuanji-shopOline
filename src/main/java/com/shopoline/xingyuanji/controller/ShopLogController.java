package com.shopoline.xingyuanji.controller;

import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.model.PayModel;
import com.shopoline.xingyuanji.model.ShopLogModel;
import com.shopoline.xingyuanji.service.db1.IShopLogService;
import com.shopoline.xingyuanji.service.db1.WxPayService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.LogisticInformationVO;
import com.shopoline.xingyuanji.vo.ShopLogInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    @RequestMapping("/pay")
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
    @ResponseBody
    @RequestMapping("/deductXingBi")
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
    @ResponseBody
    @RequestMapping("/notify")
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
    @ResponseBody
    @RequestMapping("/getOpenRandomGoods")
    public Object getOpenRandomGoods(String ticketId,String useXingBi,String isPay,String randomToken,String UUID,HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
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
    @ResponseBody
    @RequestMapping("/getShopLog")
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
    @ResponseBody
    @RequestMapping("/getShopLogInfo")
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
    @ResponseBody
    @RequestMapping("/getZIPAmount")
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
    @ResponseBody
    @RequestMapping("/sendHome")
    public Object sendHome(String ticketId,String productId,HttpServletRequest request, HttpServletResponse response){
        JsonResult json = new JsonResult();
        try {
            shopLogService.sendHome(ticketId,productId);
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
    @ResponseBody
    @RequestMapping("/deleteOrder")
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
    @ResponseBody
    @RequestMapping("/buyXingBiProduct")
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
    @ResponseBody
    @RequestMapping("/getLogisticInformation")
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

