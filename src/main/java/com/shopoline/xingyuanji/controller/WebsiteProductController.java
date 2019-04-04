package com.shopoline.xingyuanji.controller;


import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.model.WebsiteProductModel;
import com.shopoline.xingyuanji.service.db1.IWebsiteProductService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.WebsiteProductInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuty
 * @since 2019-04-04
 */
@Controller
@RequestMapping("/websiteProduct")
public class WebsiteProductController extends BaseController {


    @Autowired
    private IWebsiteProductService websiteProductService;

    /**
     * 获取官网展示产品信息
     * @param style
     * @param pageNum
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @GetMapping("/getProductInfoByStyle")
    public Object getProductInfoByStyle(String style,String pageNum,HttpServletRequest request, HttpServletResponse response){
        JsonResult<WebsiteProductInfoVO> json= new JsonResult<>();
        try {
            json.setData(websiteProductService.getProductInfoByStyle(style,pageNum));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 增加或更新官网商品
     * @param websiteProductModel
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @PutMapping("/insertWebSiteProduct")
    public Object insertWebSiteProduct(@RequestBody WebsiteProductModel websiteProductModel, HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json= new JsonResult<>();
        try {
            websiteProductService.insertWebSiteProduct(websiteProductModel);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }



}

