package com.shopoline.xingyuanji.controller;

import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.service.db2.IPrizeService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.PrizeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
    @RequestMapping("/getPrizeList")
    public Object getPrizeList(String ticketId, HttpServletRequest request, HttpServletResponse response){
        JsonResult<PrizeVO> json = new JsonResult<>();
        try {
            json.setData(prizeService.getPrizeList(ticketId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }
}
