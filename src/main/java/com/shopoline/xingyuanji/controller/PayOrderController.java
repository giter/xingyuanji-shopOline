package com.shopoline.xingyuanji.controller;


import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.utils.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author wuty
 * @since 2019-01-17
 */
@Controller
@RequestMapping("/payOrder")
public class PayOrderController extends BaseController {


    @ResponseBody
    @RequestMapping("/test")
    public Object test(String ticketId,HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            json.setData("请求成功");
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }
}

