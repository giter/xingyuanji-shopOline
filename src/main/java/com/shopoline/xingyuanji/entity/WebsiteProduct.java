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
 * @since 2019-04-04
 */
@TableName("t_website_product")
public class WebsiteProduct extends Model<WebsiteProduct> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 种类0：数码1：娱乐趣味2：美妆个护3：居家生活
     */
    private String style;
    /**
     * 图片
     */
    private String img;
    /**
     * 操作时间
     */
    private Date editTime;
    private String editBy;
    /**
     * 状态0：禁用1：启用
     */
    private String deleteFlag;


    public String getId() {
        return id;
    }

    public WebsiteProduct setId(String id) {
        this.id = id;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public WebsiteProduct setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getStyle() {
        return style;
    }

    public WebsiteProduct setStyle(String style) {
        this.style = style;
        return this;
    }

    public String getImg() {
        return img;
    }

    public WebsiteProduct setImg(String img) {
        this.img = img;
        return this;
    }

    public Date getEditTime() {
        return editTime;
    }

    public WebsiteProduct setEditTime(Date editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public WebsiteProduct setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public WebsiteProduct setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WebsiteProduct{" +
        "id=" + id +
        ", productName=" + productName +
        ", style=" + style +
        ", img=" + img +
        ", editTime=" + editTime +
        ", editBy=" + editBy +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
