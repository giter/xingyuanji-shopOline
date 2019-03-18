package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuty
 * @since 2019-03-18
 */
@TableName("t_shop_log")
public class ShopLog extends Model<ShopLog> {

    private static final long serialVersionUID = 1L;

    @TableField(value = "id")
    private String id;
    /**
     * 用户Id
     */
    @TableField(value = "userId")
    private String userId;
    /**
     * 用户微信OpenId
     */
    @TableField(value = "openId")
    private String openId;
    /**
     * 商品Id
     */
    @TableField(value = "goodsId")
    private Integer goodsId;
    /**
     * 盒子Id
     */
    @TableField(value = "boxId")
    private String boxId;
    /**
     * 操作人
     */
    @TableField(value = "editBy")
    private String editBy;
    /**
     * 操作时间
     */
    @TableField(value = "editTime")
    private Date editTime;
    /**
     * 删除状态 0：未启用 1：启用
     */
    @TableField(value = "deleteFlag")
    private Integer deleteFlag;
    /**
     * 快递去向 0：邮回家 1：送好友 2：卖了换币3：暂定
     */
    @TableField(value = "express")
    private Integer express;
    /**
     * 微信付款单号
     */
    @TableField(value = "outTradeNo")
    private String outTradeNo;
    /**
     * 运单号
     */
    @TableField(value = "tradeNo")
    private String tradeNo;
    /**
     * 是否支付成功0，否1，是
     */
    @TableField(value = "isPay")
    private String isPay;
    /**
     * 支付金额
     */
    @TableField(value = "totalFee")
    private String totalFee;
    /**
     * 邮费
     */
    @TableField(value = "ZIPAmount")
    private String ZIPAmount;
    /**
     * 邮费微信付款单号
     */
    @TableField(value = "ZIPOutTradeNo")
    private String ZIPOutTradeNo;
    /**
     * 地址Id
     */
    @TableField(value = "addressId")
    private String addressId;
    /**
     * 操作更新时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;
    /**
     * 发货 状态 0 未发货 1 已经发货
     */
    @TableField(value = "isDeliver")
    private String isDeliver;
    /**
     * 快递单号
     */
    @TableField(value = "ZIPNum")
    private String ZIPNum;


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

    public Date getUpdateTime() {
        return updateTime;
    }

    public ShopLog setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getIsDeliver() {
        return isDeliver;
    }

    public ShopLog setIsDeliver(String isDeliver) {
        this.isDeliver = isDeliver;
        return this;
    }

    public String getZIPNum() {
        return ZIPNum;
    }

    public void setZIPNum(String ZIPNum) {
        this.ZIPNum = ZIPNum;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ShopLog{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", openId='" + openId + '\'' +
                ", goodsId=" + goodsId +
                ", boxId='" + boxId + '\'' +
                ", editBy='" + editBy + '\'' +
                ", editTime=" + editTime +
                ", deleteFlag=" + deleteFlag +
                ", express=" + express +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", isPay='" + isPay + '\'' +
                ", totalFee='" + totalFee + '\'' +
                ", ZIPAmount='" + ZIPAmount + '\'' +
                ", ZIPOutTradeNo='" + ZIPOutTradeNo + '\'' +
                ", addressId='" + addressId + '\'' +
                ", updateTime=" + updateTime +
                ", isDeliver='" + isDeliver + '\'' +
                ", ZIPNum='" + ZIPNum + '\'' +
                '}';
    }
}
