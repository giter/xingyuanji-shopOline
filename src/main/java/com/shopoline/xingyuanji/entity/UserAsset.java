package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户资产表

 * </p>
 *
 * @author wuty
 * @since 2019-01-10
 */
@TableName("t_user_asset")
public class UserAsset extends Model<UserAsset>{

    private static final long serialVersionUID = 1L;

    @TableField(value = "id")
    private String id;
    @TableField(value = "userId")
    private String userId;
    /**
     * 资产数量
     */
    @TableField(value = "amount")
    private Integer amount;
    /**
     * 资产类型 0:猩币 1：签到
     */
    @TableField(value = "amountType")
    private Integer amountType;
    /**
     * 操作时间 
     */
    @TableField(value = "editTime")
    private Date editTime;
    /**
     * 操作人
     */
    @TableField(value = "editBy")
    private String editBy;
    /**
     * 状态 0：未启用 1：启用
     */
    @TableField(value = "deltFlag")
    private Integer deltFlag;
    /**
     * 用户OpenId
     */
    @TableField(value = "openId")
    private String openId;


    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public UserAsset setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public UserAsset setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Integer getAmountType() {
        return amountType;
    }

    public UserAsset setAmountTye(Integer amountType) {
        this.amountType = amountType;
        return this;
    }

    public Date getEditTime() {
        return editTime;
    }

    public UserAsset setEditTime(Date editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public UserAsset setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public Integer getDeltFlag() {
        return deltFlag;
    }

    public UserAsset setDeltFlag(Integer deltFlag) {
        this.deltFlag = deltFlag;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public UserAsset setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserAsset{" +
        "id=" + id +
        ", userId=" + userId +
        ", amount=" + amount +
        ", amountTye=" + amountType +
        ", editTime=" + editTime +
        ", editBy=" + editBy +
        ", deltFlag=" + deltFlag +
        ", openId=" + openId +
        "}";
    }
}
