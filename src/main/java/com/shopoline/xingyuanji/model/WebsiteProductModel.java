package com.shopoline.xingyuanji.model;

public class WebsiteProductModel {

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
     * 状态0：禁用1：启用
     */
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
