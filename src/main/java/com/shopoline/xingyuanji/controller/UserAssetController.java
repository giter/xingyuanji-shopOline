package com.shopoline.xingyuanji.controller;


import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.service.db1.IUserAssetService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.UserCoinVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
@Scope("prototype")
@RequestMapping("/userAsset")
public class UserAssetController extends BaseController {

    @Autowired
    private IUserAssetService userAssetService;

    /**
     * 查询用户资产表猩币数量
     */
    @ResponseBody
    @PostMapping("/quertUserCoin")
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
    @PostMapping("/exchangeCoin")
    public Object exchangeCoin(String ticketId,String goodsId, HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            json.setData(userAssetService.exchangeCoin(ticketId,goodsId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }
}

