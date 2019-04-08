package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuty
 * @since 2019-04-08
 */
@TableName("t_website_article")
public class WebsiteArticle extends Model<WebsiteArticle> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    /**
     * 标题
     */
    @TableField("title")
    private String title;
    /**
     * 正文
     */
    @TableField("text")
    private String text;
    /**
     * 作者
     */
    @TableField("author")
    private String author;
    /**
     * 创建时间
     */
    @TableField("editTime")
    private Date editTime;
    /**
     * 创建人
     */
    @TableField("editBy")
    private String editBy;
    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Date updateTime;
    /**
     * 状态 0：未启用 1：启用
     */
    @TableField("deleteFlag")
    private String deleteFlag;

    private String mark;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }


    public String getId() {
        return id;
    }

    public WebsiteArticle setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public WebsiteArticle setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public WebsiteArticle setText(String text) {
        this.text = text;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public WebsiteArticle setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Date getEditTime() {
        return editTime;
    }

    public WebsiteArticle setEditTime(Date editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public WebsiteArticle setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public WebsiteArticle setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public WebsiteArticle setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WebsiteArticle{" +
        "id=" + id +
        ", title=" + title +
        ", text=" + text +
        ", author=" + author +
        ", editTime=" + editTime +
        ", editBy=" + editBy +
        ", updateTime=" + updateTime +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
