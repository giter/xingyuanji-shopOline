package com.shopoline.xingyuanji.service.db1.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.shopoline.xingyuanji.Constants;
import com.shopoline.xingyuanji.entity.WebsiteArticle;
import com.shopoline.xingyuanji.mapper.WebsiteArticleMapper;
import com.shopoline.xingyuanji.model.ArticleTitleModel;
import com.shopoline.xingyuanji.model.WebSiteArticleModel;
import com.shopoline.xingyuanji.model.WebsiteArticleVOModel;
import com.shopoline.xingyuanji.service.db1.IWebsiteArticleService;
import com.shopoline.xingyuanji.vo.ArticleInfoVO;
import com.shopoline.xingyuanji.vo.ArticleListVO;
import com.shopoline.xingyuanji.vo.ArticleTitleListVO;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuty
 * @since 2019-04-08
 */
@Service
public class WebsiteArticleServiceImpl extends ServiceImpl<WebsiteArticleMapper, WebsiteArticle> implements IWebsiteArticleService {

    /**
     * 获取文章列表
     * @return
     */
    @Override
    public ArticleListVO getArticleList(String pageNum) {

        // 每页记录数量
        Integer pageSize = 6;
        // 根据页码计算查询条数
        Integer pageStart = (Integer.valueOf(pageNum) - 1) * pageSize;

        // 获取文章列表
        List<WebsiteArticle> websiteArticleList = baseMapper.getArticleList(pageStart,pageSize);
        ListIterator<WebsiteArticle> iterator = websiteArticleList.listIterator();
        List<WebsiteArticleVOModel> articleVOList = new LinkedList<>();
        while (iterator.hasNext()){
            WebsiteArticleVOModel websiteArticleVOModel = new WebsiteArticleVOModel();
            WebsiteArticle article = iterator.next();
            websiteArticleVOModel.setId(article.getId());
            websiteArticleVOModel.setTitle(article.getTitle());
            websiteArticleVOModel.setAuthor(article.getAuthor());
            websiteArticleVOModel.setEditTime(DateUtil(article.getEditTime()));
            websiteArticleVOModel.setEditBy(article.getEditBy());
            websiteArticleVOModel.setUpdateTime(DateUtil(article.getUpdateTime()));
            websiteArticleVOModel.setDeleteFlag(article.getDeleteFlag());
            websiteArticleVOModel.setMark(article.getMark());
            articleVOList.add(websiteArticleVOModel);
        }

        ArticleListVO articleListVO= new ArticleListVO();
        articleListVO.setArticleList(articleVOList);
        articleListVO.setCount(String.valueOf(this.selectCount(new EntityWrapper<WebsiteArticle>().eq("deleteFlag", Constants.QIYONG))));
        return articleListVO;
    }

    /**
     * 创建或更新文章
     * @param webSiteArticleModel
     */
    @Override
    public void insertArticle(WebSiteArticleModel webSiteArticleModel) {

        // 查询数据库是否存在文章
        WebsiteArticle websiteArticle = this.selectOne(new EntityWrapper<WebsiteArticle>().eq("id",webSiteArticleModel.getId()).last("Limit 1"));

        if(websiteArticle == null){

            WebsiteArticle article= new WebsiteArticle();
            article.setId(IdWorker.get32UUID());
            article.setTitle(webSiteArticleModel.getTitle());
            article.setText(webSiteArticleModel.getText());
            article.setAuthor(webSiteArticleModel.getAuthor());
            article.setEditTime(new Date());
            article.setAuthor(webSiteArticleModel.getAuthor());
            article.setEditBy(webSiteArticleModel.getEditBy());
            article.setUpdateTime(new Date());
            article.setDeleteFlag(webSiteArticleModel.getDeleteFlag());
            this.insert(article);
        }else{

            if(webSiteArticleModel.getTitle() != null){
                websiteArticle.setTitle(webSiteArticleModel.getTitle());
            }
            if(webSiteArticleModel.getText() != null){
                websiteArticle.setText(webSiteArticleModel.getText());
            }
            if(webSiteArticleModel.getAuthor() != null){
                websiteArticle.setAuthor(webSiteArticleModel.getAuthor());
            }
            if(webSiteArticleModel.getEditBy() != null){
                websiteArticle.setEditBy(webSiteArticleModel.getEditBy());
            }
            if(webSiteArticleModel.getDeleteFlag() != null){
                websiteArticle.setDeleteFlag(webSiteArticleModel.getDeleteFlag());
            }
            if(webSiteArticleModel.getMark() != null){
                websiteArticle.setMark(webSiteArticleModel.getMark());
            }
            websiteArticle.setUpdateTime(new Date());
            this.updateById(websiteArticle);
        }
    }

    /**
     * 获取文章内容
     * @param id
     * @return
     */
    @Override
    public ArticleInfoVO getArticleInfo(String id) {

        WebsiteArticle article = this.selectOne(new EntityWrapper<WebsiteArticle>().eq("id",id).last("Limit 1"));

        ArticleInfoVO articleInfo = new ArticleInfoVO();
        articleInfo.setId(article.getId());
        articleInfo.setTitle(article.getTitle());
        articleInfo.setText(article.getText());
        articleInfo.setAuthor(article.getAuthor());
        articleInfo.setEditTime(DateUtil(article.getEditTime()));
        articleInfo.setEditBy(article.getEditBy());
        articleInfo.setUpdateTime(DateUtil(article.getUpdateTime()));
        articleInfo.setDeleteFlag(article.getDeleteFlag());
        articleInfo.setMark(article.getMark());

        return articleInfo;
    }

    /**
     * 最新文章
     * @return
     */
    @Override
    public ArticleTitleListVO getArticleListByTime() {

        List<WebsiteArticle> articleList=this.selectList(new EntityWrapper<WebsiteArticle>().eq("deleteFlag",Constants.QIYONG).orderBy("editTime",false));

        ListIterator<WebsiteArticle> iterator= articleList.listIterator();
        List<ArticleTitleModel> articleTitleModelList = new LinkedList<>();
        while (iterator.hasNext()){
            WebsiteArticle websiteArticle = iterator.next();
            ArticleTitleModel articleTitleModel = new ArticleTitleModel();
            articleTitleModel.setId(websiteArticle.getId());
            articleTitleModel.setTitle(websiteArticle.getTitle());
            articleTitleModel.setEditTime(DateUtil(websiteArticle.getEditTime()));
            articleTitleModelList.add(articleTitleModel);
        }

        ArticleTitleListVO articleTitleListVO= new ArticleTitleListVO();
        articleTitleListVO.setArticleTitleList(articleTitleModelList);

        return articleTitleListVO;
    }


    private String DateUtil(Date date){
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss");
        String format = bf.format(date);
        return format;
    }

}
