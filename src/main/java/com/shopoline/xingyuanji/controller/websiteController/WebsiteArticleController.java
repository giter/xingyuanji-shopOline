package com.shopoline.xingyuanji.controller.websiteController;


import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.model.WebSiteArticleModel;
import com.shopoline.xingyuanji.service.db1.IWebsiteArticleService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.ArticleInfoVO;
import com.shopoline.xingyuanji.vo.ArticleListVO;
import com.shopoline.xingyuanji.vo.ArticleTitleListVO;
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
 * @since 2019-04-08
 */
@Controller
@RequestMapping("/websiteArticle")
public class WebsiteArticleController extends BaseController {

    @Autowired
    private IWebsiteArticleService websiteArticleService;

    /**
     * 创建或更新文章
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertArticle")
    public Object insertArticle(@RequestBody WebSiteArticleModel webSiteArticleModel,HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try {
            websiteArticleService.insertArticle(webSiteArticleModel);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 获取文章列表
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @GetMapping("/getArticleList")
    public Object getArticleList(String pageNum,HttpServletRequest request, HttpServletResponse response){
        JsonResult<ArticleListVO> json = new JsonResult<>();
        try {
            json.setData(websiteArticleService.getArticleList(pageNum));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }


    /**
     * 获取文章内容
     * @param id
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @GetMapping("/getArticleInfo")
    public Object getArticleInfo(String id,HttpServletRequest request, HttpServletResponse response){
        JsonResult<ArticleInfoVO> json = new JsonResult<>();
        try {
            json.setData(websiteArticleService.getArticleInfo(id));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 最新文章
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @GetMapping("/getArticleListByTime")
    public Object getArticleListByTime(HttpServletRequest request, HttpServletResponse response){
        JsonResult<ArticleTitleListVO> json = new JsonResult<>();
        try {
            json.setData(websiteArticleService.getArticleListByTime());
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

}

