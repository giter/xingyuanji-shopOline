package com.shopoline.xingyuanji.vo;

public class UserInfoVO {
    private String userId;
    private String nickName;
    private String openId;
    private String sex;
    private String headImgUrl;
    private String XingBiAmount;

    public String getXingBiAmount() {
        return XingBiAmount;
    }

    public void setXingBiAmount(String xingBiAmount) {
        XingBiAmount = xingBiAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
}
