package com.shopoline.xingyuanji.model;

public class SFResult {

    private String mailNo;

    private String message;

    private String orderId;

    private Integer ZIPAmount;

    private String ZIPFileName;

    public String getZIPFileName() {
        return ZIPFileName;
    }

    public void setZIPFileName(String ZIPFileName) {
        this.ZIPFileName = ZIPFileName;
    }

    public Integer getZIPAmount() {
        return ZIPAmount;
    }

    public void setZIPAmount(Integer ZIPAmount) {
        this.ZIPAmount = ZIPAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
