package com.shopoline.xingyuanji.controller;


import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.service.db1.IUserAssetService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.UserCoinVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户资产表
 前端控制器
 * </p>
 * @author wuty
 * @since 2019-01-09
 */
@Api(description = "用户资产相关接口")
@RestController
@Scope("prototype")
@RequestMapping("/userAsset")
public class UserAssetController extends BaseController {

    @Autowired
    private IUserAssetService userAssetService;

    /**
     * 查询用户资产表猩币数量
     */
    @ApiOperation(value = "查询用户资产信息" ,  notes="通过TicketId获取缓存中信息，查询用户资产总数，返回amount资产数量，amountType资产类型两个字段")
    @ApiImplicitParam(name = "ticketId" ,value = "ticketId",required = true,dataType = "String")
    @ResponseBody
    @RequestMapping(value = "/quertUserCoin",method = RequestMethod.POST)
    public Object quertUserCoin(String ticketId, HttpServletRequest request, HttpServletResponse response){
        JsonResult<UserCoinVO> json = new JsonResult<>();
        try {
            json.setData(userAssetService.quertUserCoin(ticketId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }


    /**
     * 卖了换币
     * @param ticketId
     * @param goodsId
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "卖了换币" ,  notes="用户购买完成后顶级卖了换币接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "TicketId", value = "TicketId", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsId", value = "商品Id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "shopLogId", value = "购物记录Id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/exchangeCoin",method = RequestMethod.POST)
    public Object exchangeCoin(String ticketId,String goodsId,String shopLogId, HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            json.setData(userAssetService.exchangeCoin(ticketId,goodsId,shopLogId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }
}

