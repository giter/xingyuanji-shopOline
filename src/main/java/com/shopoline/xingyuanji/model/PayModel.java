package com.shopoline.xingyuanji.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "支付对象")
public class PayModel {

    @ApiModelProperty(value = "金额",required = true)
    private String totalFee;

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }
}
