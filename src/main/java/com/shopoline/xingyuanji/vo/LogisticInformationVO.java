package com.shopoline.xingyuanji.vo;

import com.shopoline.xingyuanji.model.RespResultModel;

import java.util.LinkedList;
import java.util.List;

public class LogisticInformationVO {

    private List<RespResultModel> logisticInformation = new LinkedList<>();

    private String productName;

    private String price;

    public List<RespResultModel> getLogisticInformation() {
        return logisticInformation;
    }

    public void setLogisticInformation(List<RespResultModel> logisticInformation) {
        this.logisticInformation = logisticInformation;
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
}
