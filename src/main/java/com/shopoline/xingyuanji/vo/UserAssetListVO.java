package com.shopoline.xingyuanji.vo;

import com.shopoline.xingyuanji.model.UserAssetListModel;

import java.util.LinkedList;
import java.util.List;

public class UserAssetListVO {

    private List<UserAssetListModel> userAssetListModelList = new LinkedList<>();

    private Integer count;

    private Integer pageCount;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<UserAssetListModel> getUserAssetListModelList() {
        return userAssetListModelList;
    }

    public void setUserAssetListModelList(List<UserAssetListModel> userAssetListModelList) {
        this.userAssetListModelList = userAssetListModelList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
