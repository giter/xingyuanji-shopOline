package com.shopoline.xingyuanji.controller;


import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.model.SignModel;
import com.shopoline.xingyuanji.service.IUserInfoService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户信息
 前端控制器
 * </p>
 * @author wuty
 * @since 2019-01-09
 */
@Controller
@Scope("prototype")
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {

    @Autowired
    private IUserInfoService userInfoService;


    /**
     * 微信登陆
     * @param code
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public Object login(String code, HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            json.setData(userInfoService.WXLogin(code));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 获取个人信息
     * @param ticketId
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserInfo")
    public Object getUserInfo(String ticketId,HttpServletRequest request, HttpServletResponse response){
        JsonResult<UserInfoVO> json = new JsonResult<>();
        try {

            json.setData(userInfoService.getUserInfo(ticketId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }


    /**
     * 获取Sign
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getSign")
    public Object getSign(HttpServletRequest request, HttpServletResponse response){
        JsonResult<SignModel> json = new JsonResult<>();
        try {
            json.setData(userInfoService.getSign());
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }



}

