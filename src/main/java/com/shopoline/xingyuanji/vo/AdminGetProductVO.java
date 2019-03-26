package com.shopoline.xingyuanji.vo;

import com.shopoline.xingyuanji.model.ProductListModel;

import java.util.LinkedList;
import java.util.List;

public class AdminGetProductVO {

    List<ProductListModel> productList = new LinkedList<>();

    private String pageCount;

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public List<ProductListModel> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListModel> productList) {
        this.productList = productList;
    }
}
