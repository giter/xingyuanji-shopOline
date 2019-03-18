package com.shopoline.xingyuanji.vo;

import com.shopoline.xingyuanji.model.UserShopLogInfoModel;

import java.util.LinkedList;
import java.util.List;

public class UserShopLogInfoVO {

    private List<UserShopLogInfoModel> userShopLogInfolList = new LinkedList<>();

    private Integer pageCount;

    public List<UserShopLogInfoModel> getUserShopLogInfolList() {
        return userShopLogInfolList;
    }

    public void setUserShopLogInfolList(List<UserShopLogInfoModel> userShopLogInfolList) {
        this.userShopLogInfolList = userShopLogInfolList;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
