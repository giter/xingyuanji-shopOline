package com.shopoline.xingyuanji.controller;


import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.model.SignModel;
import com.shopoline.xingyuanji.service.db1.IUserInfoService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
 * 用户信息
 前端控制器
 * </p>
 * @author wuty
 * @since 2019-01-09
 */
@Api(description = "用户接口")
@RestController
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
    @ApiOperation(value = "用户登录" ,  notes="前端请求微信Auth2接口返回CODE参数，通过CODE值请求微信获取用户信息API获取USERINFO并存入t_user_info表，" +
            "并在REDIS中存入ticketId（REDIS中结构：KEY:ticketId。Value：openId）" )
    @ApiImplicitParam(name = "code" ,value = "前端请求微信统一认证接口返回code参数",required = true,dataType = "String",paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
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
    @ApiOperation(value = "获取用户信息" ,  notes="通过ticketId获取redis中相应的VALUE，通过VALUE（openId）获取t_user_info表中响应信息" )
    @ApiImplicitParam(name = "ticketId" ,value = "ticketId",required = true,dataType = "String",paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
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
    @ApiOperation(value = "获取微信SIGN" ,  notes="前端废除微信下部后退按钮需要SIGN，只用一次，业务没有用到" )
    @ResponseBody
    @RequestMapping(value = "/getSign",method = RequestMethod.POST)
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

