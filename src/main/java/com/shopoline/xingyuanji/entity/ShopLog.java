package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuty
 * @since 2019-02-28
 */
@TableName("t_shop_log")
public class ShopLog extends Model<ShopLog> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 用户微信OpenId
     */
    private String openId;
    /**
     * 商品Id
     */
    private Integer goodsId;
    /**
     * 盒子Id
     */
    private String boxId;
    /**
     * 操作人
     */
    private String editBy;
    /**
     * 操作时间
     */
    private Date editTime;
    /**
     * 删除状态 0：未启用 1：启用
     */
    private Integer deleteFlag;
    /**
     * 快递去向 0：邮回家 1：送好友 2：卖了换币3：暂定
     */
    private Integer express;
    /**
     * 微信付款单号
     */
    private String outTradeNo;
    /**
     * 运单号
     */
    private String tradeNo;
    /**
     * 是否支付成功0，否1，是
     */
    private String isPay;
    /**
     * 支付金额
     */
    private String totalFee;
    /**
     * 邮费
     */
    private String ZIPAmount;
    /**
     * 邮费微信付款单号
     */
    private String ZIPOutTradeNo;
    /**
     * 地址Id
     */
    private String addressId;


    public String getId() {
        return id;
    }

    public ShopLog setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public ShopLog setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public ShopLog setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public ShopLog setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public String getBoxId() {
        return boxId;
    }

    public ShopLog setBoxId(String boxId) {
        this.boxId = boxId;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public ShopLog setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public Date getEditTime() {
        return editTime;
    }

    public ShopLog setEditTime(Date editTime) {
        this.editTime = editTime;
        return this;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public ShopLog setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }

    public Integer getExpress() {
        return express;
    }

    public ShopLog setExpress(Integer express) {
        this.express = express;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public ShopLog setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public ShopLog setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
        return this;
    }

    public String getIsPay() {
        return isPay;
    }

    public ShopLog setIsPay(String isPay) {
        this.isPay = isPay;
        return this;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public ShopLog setTotalFee(String totalFee) {
        this.totalFee = totalFee;
        return this;
    }

    public String getZIPAmount() {
        return ZIPAmount;
    }

    public ShopLog setZIPAmount(String ZIPAmount) {
        this.ZIPAmount = ZIPAmount;
        return this;
    }

    public String getZIPOutTradeNo() {
        return ZIPOutTradeNo;
    }

    public ShopLog setZIPOutTradeNo(String ZIPOutTradeNo) {
        this.ZIPOutTradeNo = ZIPOutTradeNo;
        return this;
    }

    public String getAddressId() {
        return addressId;
    }

    public ShopLog setAddressId(String addressId) {
        this.addressId = addressId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ShopLog{" +
        "id=" + id +
        ", userId=" + userId +
        ", openId=" + openId +
        ", goodsId=" + goodsId +
        ", boxId=" + boxId +
        ", editBy=" + editBy +
        ", editTime=" + editTime +
        ", deleteFlag=" + deleteFlag +
        ", express=" + express +
        ", outTradeNo=" + outTradeNo +
        ", tradeNo=" + tradeNo +
        ", isPay=" + isPay +
        ", totalFee=" + totalFee +
        ", ZIPAmount=" + ZIPAmount +
        ", ZIPOutTradeNo=" + ZIPOutTradeNo +
        ", addressId=" + addressId +
        "}";
    }
}
