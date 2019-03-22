package com.shopoline.xingyuanji.vo;

import com.shopoline.xingyuanji.model.DaysSellCountModel;
import com.shopoline.xingyuanji.model.EveryDaySellAmountModel;
import com.shopoline.xingyuanji.model.SellCountModel;
import com.shopoline.xingyuanji.model.SellDataModel;

import java.util.LinkedList;
import java.util.List;

public class DataVO {

    private SellDataModel sellDataModel;

    private SellCountModel sellCountModel;

    private List<DaysSellCountModel> daysSellCountModelList = new LinkedList<>();

    private List<EveryDaySellAmountModel> everyDaySellAmountModelList = new LinkedList<>();

    public SellDataModel getSellDataModel() {
        return sellDataModel;
    }

    public void setSellDataModel(SellDataModel sellDataModel) {
        this.sellDataModel = sellDataModel;
    }

    public SellCountModel getSellCountModel() {
        return sellCountModel;
    }

    public void setSellCountModel(SellCountModel sellCountModel) {
        this.sellCountModel = sellCountModel;
    }

    public List<DaysSellCountModel> getDaysSellCountModelList() {
        return daysSellCountModelList;
    }

    public void setDaysSellCountModelList(List<DaysSellCountModel> daysSellCountModelList) {
        this.daysSellCountModelList = daysSellCountModelList;
    }

    public List<EveryDaySellAmountModel> getEveryDaySellAmountModelList() {
        return everyDaySellAmountModelList;
    }

    public void setEveryDaySellAmountModelList(List<EveryDaySellAmountModel> everyDaySellAmountModelList) {
        this.everyDaySellAmountModelList = everyDaySellAmountModelList;
    }
}
