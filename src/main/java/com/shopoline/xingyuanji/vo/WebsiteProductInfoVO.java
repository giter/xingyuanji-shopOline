package com.shopoline.xingyuanji.vo;

import com.shopoline.xingyuanji.entity.WebsiteProduct;

import java.util.LinkedList;
import java.util.List;

public class WebsiteProductInfoVO {

    private List<WebsiteProduct> websiteProductList = new LinkedList<>();

    private Integer pagrCount;

    public Integer getPagrCount() {
        return pagrCount;
    }

    public void setPagrCount(Integer pagrCount) {
        this.pagrCount = pagrCount;
    }

    public List<WebsiteProduct> getWebsiteProductList() {
        return websiteProductList;
    }

    public void setWebsiteProductList(List<WebsiteProduct> websiteProductList) {
        this.websiteProductList = websiteProductList;
    }
}
