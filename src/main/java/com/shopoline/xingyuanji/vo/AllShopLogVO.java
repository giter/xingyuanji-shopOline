package com.shopoline.xingyuanji.vo;

import com.shopoline.xingyuanji.model.UserShopLogInfoModel;

import java.util.LinkedList;
import java.util.List;

public class AllShopLogVO {

    List<UserShopLogInfoModel> allShopLogList = new LinkedList<>();

    private String pageCount;

    public List<UserShopLogInfoModel> getAllShopLogList() {
        return allShopLogList;
    }

    public void setAllShopLogList(List<UserShopLogInfoModel> allShopLogList) {
        this.allShopLogList = allShopLogList;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }
}
