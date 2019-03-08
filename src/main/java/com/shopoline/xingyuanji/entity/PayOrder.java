package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuty
 * @since 2019-01-17
 */
@TableName("t_pay_order")
public class PayOrder extends Model<PayOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 支付订单号
     */
    @TableField("pay_trade_no")
    private String payTradeNo;
    /**
     * 机器id
     */
    @TableField("machine_id")
    private String machineId;
    /**
     * 总金额单位为分，或者积分，
     */
    @TableField("total_fee")
    private Integer totalFee;
    /**
     * 支付类型，1为微信，2为积分
     */
    @TableField("pay_type")
    private Integer payType;
    /**
     * 支付状态，0待支付，1支付成功
     */
    @TableField("pay_status")
    private Integer payStatus;
    /**
     * 购买商品总数
     */
    @TableField("buy_num")
    private Integer buyNum;
    /**
     * 出货商品总数
     */
    @TableField("out_num")
    private Integer outNum;
    /**
     * 描述
     */
    @TableField("detail")
    private String detail;
    /**
     * 插入时间
     */
    @TableField("insert_time")
    private Date insertTime;
    /**
     * 插入id，即购买人id
     */
    @TableField("insert_id")
    private Integer insertId;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 更新人id
     */
    @TableField("update_id")
    private Integer updateId;


    public Integer getId() {
        return id;
    }

    public PayOrder setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getPayTradeNo() {
        return payTradeNo;
    }

    public PayOrder setPayTradeNo(String payTradeNo) {
        this.payTradeNo = payTradeNo;
        return this;
    }

    public String getMachineId() {
        return machineId;
    }

    public PayOrder setMachineId(String machineId) {
        this.machineId = machineId;
        return this;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public PayOrder setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
        return this;
    }

    public Integer getPayType() {
        return payType;
    }

    public PayOrder setPayType(Integer payType) {
        this.payType = payType;
        return this;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public PayOrder setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
        return this;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public PayOrder setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
        return this;
    }

    public Integer getOutNum() {
        return outNum;
    }

    public PayOrder setOutNum(Integer outNum) {
        this.outNum = outNum;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public PayOrder setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public PayOrder setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public Integer getInsertId() {
        return insertId;
    }

    public PayOrder setInsertId(Integer insertId) {
        this.insertId = insertId;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public PayOrder setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public PayOrder setUpdateId(Integer updateId) {
        this.updateId = updateId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PayOrder{" +
        "id=" + id +
        ", payTradeNo=" + payTradeNo +
        ", machineId=" + machineId +
        ", totalFee=" + totalFee +
        ", payType=" + payType +
        ", payStatus=" + payStatus +
        ", buyNum=" + buyNum +
        ", outNum=" + outNum +
        ", detail=" + detail +
        ", insertTime=" + insertTime +
        ", insertId=" + insertId +
        ", updateTime=" + updateTime +
        ", updateId=" + updateId +
        "}";
    }
}
