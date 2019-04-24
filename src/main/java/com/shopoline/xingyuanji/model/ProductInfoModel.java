package com.shopoline.xingyuanji.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "商品详情对象",description = "ProductInfoModel")
public class ProductInfoModel {

    @ApiModelProperty(value = "商品id",required = true)
    private String id;

    @ApiModelProperty(value = "商品名称",required = false)
    private String productName;

    @ApiModelProperty(value = "商品价格",required = false)
    private String price;

    @ApiModelProperty(value = "商品数量",required = false)
    private String productNum;

    @ApiModelProperty(value = "商品类型0：开盒商品1：积分兑换商品2，盒子",required = false)
    private String style;

    @ApiModelProperty(value = "商品积分值",required = false)
    private String socer;

    @ApiModelProperty(value = "商品状态0禁用1启用",required = false)
    private String status;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getSocer() {
        return socer;
    }

    public void setSocer(String socer) {
        this.socer = socer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
