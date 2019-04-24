package com.shopoline.xingyuanji.controller;

import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.model.PrizeModel;
import com.shopoline.xingyuanji.service.db2.IPrizeService;
import com.shopoline.xingyuanji.utils.JSONUtil;
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



@Api(description = "红包相关接口")
@Controller
@Scope("prototype")
@RequestMapping("/prizeLog")
public class PrizeLogController extends BaseController {

    @Autowired
    private IPrizeService prizeService;

    /**
     * 获取用户奖品列表
     * @param ticketId
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "获取用户奖品列表" ,  notes="获取用户奖品列表")
    @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String",paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/getPrizeList",method = RequestMethod.POST)
    public Object getPrizeList(String ticketId, HttpServletRequest request, HttpServletResponse response){
        JsonResult<List<PrizeModel>> json = new JsonResult<>();
        try {
            json.setData(prizeService.getPrizeList(ticketId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 用户兑奖
     * @param ticketId
     * @param prizeId
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "获取用户奖品列表" ,  notes="获取用户奖品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "prizeId" ,value ="奖品Id",required = true, dataType = "String",paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/cashPrize",method = RequestMethod.POST)
    public Object cashPrize(String ticketId, String prizeId,HttpServletRequest request, HttpServletResponse response){
        JsonResult<String> json = new JsonResult<>();
        try {
            json.setData(prizeService.cashPrize(ticketId,prizeId,request));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 创建二维码
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "创建抽奖用二维码" ,  notes="创建抽奖用二维码")
    @ApiImplicitParam(name = "num" ,value ="生成数量",required = true, dataType = "int",paramType = "query")
    @ResponseBody
    @PutMapping(value = "/saveCode")
    public Object saveCode( int num,HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            prizeService.saveCode(num);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    @ApiOperation(value = "测试接口" ,  notes="测试接口")
    @ResponseBody
    @PutMapping("/savePrize")
    public Object savePrize(HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            prizeService.savePrize();
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

}
