package com.shopoline.xingyuanji.model;

public class SellDataModel {

    private Integer BoxProductSellCount;
    // 获取积分商品销售总量
    private Integer SocerProductSellCount;
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

    public Integer getBoxProductSellCount() {
        return BoxProductSellCount;
    }

    public void setBoxProductSellCount(Integer boxProductSellCount) {
        BoxProductSellCount = boxProductSellCount;
    }

    public Integer getSocerProductSellCount() {
        return SocerProductSellCount;
    }

    public void setSocerProductSellCount(Integer socerProductSellCount) {
        SocerProductSellCount = socerProductSellCount;
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
