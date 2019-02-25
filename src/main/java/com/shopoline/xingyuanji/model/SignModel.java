package com.shopoline.xingyuanji.model;

public class SignModel {

    private String APPID;

    private String timeStamp;

    private String nonceStr;

    private String signAture;

    public String getAPPID() {
        return APPID;
    }

    public void setAPPID(String APPID) {
        this.APPID = APPID;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignAture() {
        return signAture;
    }

    public void setSignAture(String signAture) {
        this.signAture = signAture;
    }
}
