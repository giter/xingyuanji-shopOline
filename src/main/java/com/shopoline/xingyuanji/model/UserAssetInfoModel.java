package com.shopoline.xingyuanji.model;


import java.util.Date;

public class UserAssetInfoModel {

    private String userId;

    private String openId;

    private String nickName;

    private Date editTime;


    private String deletFlag;

    private String amountId;

    private String amount;

    private String amountType;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }


    public String getDeletFlag() {
        return deletFlag;
    }

    public void setDeletFlag(String deletFlag) {
        this.deletFlag = deletFlag;
    }

    public String getAmountId() {
        return amountId;
    }

    public void setAmountId(String amountId) {
        this.amountId = amountId;
    }
}
