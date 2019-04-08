package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.activerecord.Model;
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
@TableName("t_wedsite_article")
public class WedsiteArticle extends Model<WedsiteArticle> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 正文
     */
    private String text;
    /**
     * 作者
     */
    private String author;
    /**
     * 创建时间
     */
    private Date editTime;
    /**
     * 创建人
     */
    private String editBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 状态 0：未启用 1：启用
     */
    private String deleteFlag;


    public Integer getId() {
        return id;
    }

    public WedsiteArticle setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public WedsiteArticle setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public WedsiteArticle setText(String text) {
        this.text = text;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public WedsiteArticle setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Date getEditTime() {
        return editTime;
    }

    public WedsiteArticle setEditTime(Date editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public WedsiteArticle setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public WedsiteArticle setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public WedsiteArticle setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WedsiteArticle{" +
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
