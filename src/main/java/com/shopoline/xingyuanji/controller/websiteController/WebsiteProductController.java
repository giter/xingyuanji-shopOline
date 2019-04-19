package com.shopoline.xingyuanji.controller.websiteController;


import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.model.WebsiteProductModel;
import com.shopoline.xingyuanji.service.db1.IWebsiteProductService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.WebsiteProductInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "官网展示接口")
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
    @ApiOperation(value = "获取官网展示商品信息" ,  notes="获取官网展示商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "style" ,value ="商品种类0：数码1：娱乐趣味2：美妆个护3：居家生活",required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageNum" ,value ="页码",required = true, dataType = "String")
            })
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
    @ApiOperation(value = "增加或更新官网商品" ,  notes="增加或更新官网商品")
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

