package com.shopoline.xingyuanji.vo;

import com.shopoline.xingyuanji.entity.ProductInfo;

public class AfterPaySuccessVO {

    private ProductInfo productInfo;

    private String shopLogId;

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public String getShopLogId() {
        return shopLogId;
    }

    public void setShopLogId(String shopLogId) {
        this.shopLogId = shopLogId;
    }
}
