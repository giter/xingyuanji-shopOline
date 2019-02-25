package com.shopoline.xingyuanji.vo;

public class ProductInfoVO {

    private String goodsname;
    /**
     * 价格
     */
    private Float price;
    /**
     * 商品图片链接
     */
    private String img;
    /**
     * 商品数量
     */
    private Integer count;
    /**
     * 商品类型 0：盒子商品  1：积分商品
     */
    private String style;
    /**
     * 商品分类：
     */
    private String kind;
    /**
     * 所需积分数量
     */
    private String socer;

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSocer() {
        return socer;
    }

    public void setSocer(String socer) {
        this.socer = socer;
    }
}
