package com.shopoline.xingyuanji.controller;

import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.model.PrizeModel;
import com.shopoline.xingyuanji.service.db2.IPrizeService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


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
    @ResponseBody
    @PostMapping(value = "/getPrizeList")
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
    @ResponseBody
    @PostMapping(value = "/cashPrize")
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


}
