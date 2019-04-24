package com.shopoline.xingyuanji.controller;

import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.model.ImgUploadVM;
import com.shopoline.xingyuanji.service.db1.IAdminInfoService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Api(description = "图片上传接口")
@Controller
@RequestMapping("/upload")
@Scope("prototype")
public class UploadController extends BaseController {

    @Autowired
    private IAdminInfoService adminInfoService;

    private final ResourceLoader resourceLoader;

    @Autowired
    public UploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    /**
     * 上传图片
     * @param file
     * @param style
     * @return
     */
    @ApiOperation(value = "上传图片" ,  notes="上传图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "style" ,value ="图片类型（0：盒子商品1：积分商品2：积分商品展示图）",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "productId" ,value ="商品Id",required = true, dataType = "String",paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/uploadImage",method = RequestMethod.POST)
    public String uploadImage(@RequestParam(value = "file") MultipartFile file, String style, String productId, HttpServletRequest request, HttpServletResponse response){
        JsonResult<ImgUploadVM> json = new JsonResult<>();
        try{
            json.setData(adminInfoService.uploadImg(file,style,productId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

}
