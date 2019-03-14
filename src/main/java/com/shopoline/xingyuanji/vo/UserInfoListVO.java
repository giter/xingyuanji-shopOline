package com.shopoline.xingyuanji.vo;

import com.shopoline.xingyuanji.model.UserInfoListModel;

import java.util.LinkedList;
import java.util.List;

public class UserInfoListVO {

    private Integer count;

    List<UserInfoListModel> userInfoListModelList = new LinkedList<>();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserInfoListModel> getUserInfoListModelList() {
        return userInfoListModelList;
    }

    public void setUserInfoListModelList(List<UserInfoListModel> userInfoListModelList) {
        this.userInfoListModelList = userInfoListModelList;
    }
}
