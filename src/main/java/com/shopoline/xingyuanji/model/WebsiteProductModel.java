package com.shopoline.xingyuanji.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "官网展示商品对象")
public class WebsiteProductModel {

    @ApiModelProperty(value = "id",required = true)
    private String id;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称",required = true)
    private String productName;
    /**
     * 种类0：数码1：娱乐趣味2：美妆个护3：居家生活
     */
    @ApiModelProperty(value = "种类0：数码1：娱乐趣味2：美妆个护3：居家生活",required = true)
    private String style;
    /**
     * 图片
     */
    @ApiModelProperty(value = "图片",required = true)
    private String img;
    /**
     * 状态0：禁用1：启用
     */
    @ApiModelProperty(value = "状态0：禁用1：启用",required = true)
    private String deleteFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
