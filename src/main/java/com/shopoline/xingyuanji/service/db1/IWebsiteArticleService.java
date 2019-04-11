package com.shopoline.xingyuanji.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.shopoline.xingyuanji.entity.WebsiteArticle;
import com.shopoline.xingyuanji.model.WebSiteArticleModel;
import com.shopoline.xingyuanji.vo.ArticleInfoVO;
import com.shopoline.xingyuanji.vo.ArticleListVO;
import com.shopoline.xingyuanji.vo.ArticleTitleListVO;

/**
 * <p>
 *  服务类
 * </p>
 * @author wuty
 * @since 2019-04-08
 */
public interface IWebsiteArticleService extends IService<WebsiteArticle> {

    /**
     * 获取文章列表
     * @return
     */
    ArticleListVO getArticleList(String pageNum);

    /**
     * 创建或更新文章
     * @param webSiteArticleModel
     * @return
     */
    void insertArticle(WebSiteArticleModel webSiteArticleModel);

    /**
     * 获取文章内容
     * @param id
     * @return
     */
    ArticleInfoVO getArticleInfo(String id);

    /**
     * 最新文章
     * @return
     */
    ArticleTitleListVO getArticleListByTime();

}
