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
 * @since 2019-01-09
 */
@TableName("t_product_info")
public class ProductInfo extends Model<ProductInfo> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 商品名称
     */
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
    private Integer productCount;
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
    /**
     * 操作时间
     */
    private Date editTime;
    /**
     * 操作人
     */
    private String editBy;
    /**
     * 是否启用 0：否 1：是
     */
    private String deleteFlag;


    public Integer getId() {
        return id;
    }

    public ProductInfo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public ProductInfo setGoodsname(String goodsname) {
        this.goodsname = goodsname;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public ProductInfo setPrice(Float price) {
        this.price = price;
        return this;
    }

    public String getImg() {
        return img;
    }

    public ProductInfo setImg(String img) {
        this.img = img;
        return this;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
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

    public ProductInfo setKind(String kind) {
        this.kind = kind;
        return this;
    }

    public String getSocer() {
        return socer;
    }

    public ProductInfo setSocer(String socer) {
        this.socer = socer;
        return this;
    }

    public Date getEditTime() {
        return editTime;
    }

    public ProductInfo setEditTime(Date editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public ProductInfo setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public ProductInfo setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id=" + id +
                ", goodsname='" + goodsname + '\'' +
                ", price=" + price +
                ", img='" + img + '\'' +
                ", productCount=" + productCount +
                ", style='" + style + '\'' +
                ", kind='" + kind + '\'' +
                ", socer='" + socer + '\'' +
                ", editTime=" + editTime +
                ", editBy='" + editBy + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                '}';
    }
}
