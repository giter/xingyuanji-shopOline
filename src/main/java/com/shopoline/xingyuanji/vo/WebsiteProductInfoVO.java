package com.shopoline.xingyuanji.vo;

import com.shopoline.xingyuanji.entity.WebsiteProduct;

import java.util.LinkedList;
import java.util.List;

public class WebsiteProductInfoVO {

    private List<WebsiteProduct> websiteProductList = new LinkedList<>();

    public List<WebsiteProduct> getWebsiteProductList() {
        return websiteProductList;
    }

    public void setWebsiteProductList(List<WebsiteProduct> websiteProductList) {
        this.websiteProductList = websiteProductList;
    }
}
