package com.shopoline.xingyuanji.vo;

import com.shopoline.xingyuanji.model.PrizeModel;

import java.util.LinkedList;
import java.util.List;

public class PrizeVO {

    private List<PrizeModel> prizeList = new LinkedList<>();

    public List<PrizeModel> getPrizeList() {
        return prizeList;
    }

    public void setPrizeList(List<PrizeModel> prizeList) {
        this.prizeList = prizeList;
    }
}
