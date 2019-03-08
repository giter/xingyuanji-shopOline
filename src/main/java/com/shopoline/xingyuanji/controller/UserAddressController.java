package com.shopoline.xingyuanji.controller;


import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.entity.UserAddress;
import com.shopoline.xingyuanji.model.UserAddressModel;
import com.shopoline.xingyuanji.service.db1.IUserAddressService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    @RequestMapping("/setAdderss")
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
    @ResponseBody
    @RequestMapping("/getAdderss")
    public Object getAdderss(String ticketId,HttpServletRequest request, HttpServletResponse response) {
        JsonResult<List<UserAddress>> json = new JsonResult<>();
        try {
            json.setData(userAddressService.getAdress(ticketId));
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
    @ResponseBody
    @RequestMapping("/setAddressDef")
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
    @ResponseBody
    @RequestMapping("/deleteAddress")
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
    @ResponseBody
    @RequestMapping("/updateAddress")
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
    @ResponseBody
    @RequestMapping("/isUserAddress")
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

