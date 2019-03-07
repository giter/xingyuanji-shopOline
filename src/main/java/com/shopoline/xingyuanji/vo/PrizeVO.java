package com.shopoline.xingyuanji.vo;

import com.shopoline.xingyuanji.model.PrizeModel;

import java.util.LinkedList;
import java.util.List;

public class PrizeVO {

    List<PrizeModel> prizeModelList = new LinkedList<>();

    public List<PrizeModel> getPrizeModelList() {
        return prizeModelList;
    }

    public void setPrizeModelList(List<PrizeModel> prizeModelList) {
        this.prizeModelList = prizeModelList;
    }
}
