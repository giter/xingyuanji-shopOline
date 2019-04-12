package com.shopoline.xingyuanji.vo;

public class LoginVO {

    private String code;
    private String ticketId;
    private String channelCode;
    private String hasUserInfo;

    public String getHasUserInfo() {
        return hasUserInfo;
    }

    public void setHasUserInfo(String hasUserInfo) {
        this.hasUserInfo = hasUserInfo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

}
