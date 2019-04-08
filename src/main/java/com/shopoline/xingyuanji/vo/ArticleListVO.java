package com.shopoline.xingyuanji.vo;

import com.shopoline.xingyuanji.model.WebsiteArticleVOModel;

import java.util.LinkedList;
import java.util.List;

public class ArticleListVO {


    private List<WebsiteArticleVOModel> articleList = new LinkedList<>();

    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<WebsiteArticleVOModel> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<WebsiteArticleVOModel> articleList) {
        this.articleList = articleList;
    }
}
