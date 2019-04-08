package com.shopoline.xingyuanji.vo;

import com.shopoline.xingyuanji.model.ArticleTitleModel;

import java.util.LinkedList;
import java.util.List;

public class ArticleTitleListVO {

    List<ArticleTitleModel> articleTitleList = new LinkedList<>();

    public List<ArticleTitleModel> getArticleTitleList() {
        return articleTitleList;
    }

    public void setArticleTitleList(List<ArticleTitleModel> articleTitleList) {
        this.articleTitleList = articleTitleList;
    }
}
