package com.shopoline.xingyuanji.controller;


import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.entity.UserAddress;
import com.shopoline.xingyuanji.model.UserAddressModel;
import com.shopoline.xingyuanji.service.db1.IUserAddressService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 用户邮寄地址 前端控制器
 * </p>
 * @author wuty
 * @since 2019-01-09
 */
@Api(description = "用户地址相关接口")
@Controller
@Scope("prototype")
@RequestMapping("/userAddress")
public class UserAddressController extends BaseController {

    @Autowired
    private IUserAddressService userAddressService;


    /**
     * 写入用户地址
     * @param ticketId
     * @param userAddressModel
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "写入地址信息" ,  notes="写入用户地址信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String",paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/setAdderss",method = RequestMethod.POST)
    public Object setAdderss(String ticketId, @RequestBody UserAddressModel userAddressModel, HttpServletRequest request, HttpServletResponse response) {
        JsonResult<Object> json = new JsonResult<>();
        try {
            userAddressService.insertAddress(ticketId,userAddressModel);
        } catch (Exception e) {
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 获取用户地址
     * @param ticketId
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "获取用户地址" ,  notes="获取用户地址")
    @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String",paramType = "query")
    @ResponseBody
    @GetMapping(value = "/getAdderss")
    public Object getAdderss(String ticketId,HttpServletRequest request, HttpServletResponse response) {
        JsonResult<List<UserAddress>> json = new JsonResult<>();
        try {
            json.setData(userAddressService.getAddress(ticketId));
        } catch (Exception e) {
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 设置默认地址
     * @param ticketId
     * @param id
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "设置默认地址" ,  notes="设置默认地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "id" ,value ="用户地址信息ID",required = true, dataType = "String",paramType = "query"),
    })
    @ResponseBody
    @RequestMapping(value = "/setAddressDef",method = RequestMethod.POST)
    public Object setAddressDef(String ticketId,String id,HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            userAddressService.setAddressDef(ticketId,id);
        } catch (Exception e) {
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 删除地址
     * @param ticketId
     * @param id
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "设置默认地址" ,  notes="设置默认地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "id" ,value ="用户地址信息ID",required = true, dataType = "String",paramType = "query"),
    })
    @ResponseBody
    @RequestMapping(value = "/deleteAddress",method = RequestMethod.POST)
    public Object deleteAddress(String ticketId,String id,HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            userAddressService.deleteAddress(ticketId,id);
        } catch (Exception e) {
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 更新地址
     * @param ticketId
     * @param id
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "更新地址" ,  notes="更新地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "id" ,value ="用户地址信息ID",required = true, dataType = "String",paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/updateAddress",method = RequestMethod.POST)
    public Object updateAddress(String ticketId,String id,@RequestBody UserAddressModel userAddressModel,HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            userAddressService.updateAddress(ticketId,id,userAddressModel);
        } catch (Exception e) {
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 判断用户是否有默认地址
     * @param ticketId
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "判断用户是否有默认地址" ,  notes="判断用户是否有默认地址")
    @ApiImplicitParam(name = "ticketId" ,value ="ticketId",required = true, dataType = "String",paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/isUserAddress",method = RequestMethod.POST)
    public Object isUserAddress(String ticketId,HttpServletRequest request, HttpServletResponse response){
        JsonResult<String> json = new JsonResult<>();
        try {
            json.setData(userAddressService.isUserAddress(ticketId));
        } catch (Exception e) {
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

}

