package com.shopoline.xingyuanji.model;

import java.util.LinkedList;
import java.util.List;

public class SellDataModel {

    private List<BoxProductSellCountModel> boxProductSellCountModels = new LinkedList<>();
    // 获取积分商品销售总量
    private  List<SocerProductSellCountModel> socerProductSellCountModelList = new LinkedList<>();
    // 获取邮回家商品数量
    private Integer MailToHomeProductCount;
    // 获取换猩币商品数量
    private Integer ChangeXingBiProduct;
    // 获取待定商品数量
    private Integer DaiDingProduct;
    // 获取销售历史总金额
    private Integer SellAmountHistory;

    private Integer ZIPAmountHistory;

    public Integer getZIPAmountHistory() {
        return ZIPAmountHistory;
    }

    public void setZIPAmountHistory(Integer ZIPAmountHistory) {
        this.ZIPAmountHistory = ZIPAmountHistory;
    }

    public List<BoxProductSellCountModel> getBoxProductSellCountModels() {
        return boxProductSellCountModels;
    }

    public void setBoxProductSellCountModels(List<BoxProductSellCountModel> boxProductSellCountModels) {
        this.boxProductSellCountModels = boxProductSellCountModels;
    }

    public List<SocerProductSellCountModel> getSocerProductSellCountModelList() {
        return socerProductSellCountModelList;
    }

    public void setSocerProductSellCountModelList(List<SocerProductSellCountModel> socerProductSellCountModelList) {
        this.socerProductSellCountModelList = socerProductSellCountModelList;
    }

    public Integer getMailToHomeProductCount() {
        return MailToHomeProductCount;
    }

    public void setMailToHomeProductCount(Integer mailToHomeProductCount) {
        MailToHomeProductCount = mailToHomeProductCount;
    }

    public Integer getChangeXingBiProduct() {
        return ChangeXingBiProduct;
    }

    public void setChangeXingBiProduct(Integer changeXingBiProduct) {
        ChangeXingBiProduct = changeXingBiProduct;
    }

    public Integer getDaiDingProduct() {
        return DaiDingProduct;
    }

    public void setDaiDingProduct(Integer daiDingProduct) {
        DaiDingProduct = daiDingProduct;
    }

    public Integer getSellAmountHistory() {
        return SellAmountHistory;
    }

    public void setSellAmountHistory(Integer sellAmountHistory) {
        SellAmountHistory = sellAmountHistory;
    }
}
