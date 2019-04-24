package com.shopoline.xingyuanji.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "官网文章对象")
public class WebSiteArticleModel {

    @ApiModelProperty(value = "文章id",required = false)
   private String id;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题",required = false)
    private String title;
    /**
     * 正文
     */
    @ApiModelProperty(value = "正文",required = false)
    private String text;
    /**
     * 作者
     */
    @ApiModelProperty(value = "作者",required = false)
    private String author;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人",required = false)
    private String editBy;
    /**
     * 状态 0：未启用 1：启用
     */
    @ApiModelProperty(value = "状态 0：未启用 1：启用",required = false)
    private String deleteFlag;

    @ApiModelProperty(value = "备注",required = false)
    private String mark;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
