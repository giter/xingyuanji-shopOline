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
 * 兑奖记录
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
@TableName("prize_log")
public class PrizeLog extends Model<PrizeLog>{

    private static final long serialVersionUID = 1L;

    /**
     * 兑奖记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 扫描的二维码id
     */
    @TableField("src_id")
    private String srcId;
    /**
     * 奖品id
     */
    @TableField("prize_id")
    private Integer prizeId;
    /**
     * 价格，单位分
     */
    private Integer price;
    /**
     * 兑奖人id
     */
    @TableField("buyer_id")
    private Integer buyerId;
    /**
     * 地址
     */
    private String address;
    /**
     * 收货人姓名
     */
    private String adname;
    /**
     * 收货人手机号
     */
    private String adphone;
    /**
     * 兑奖状态，1未兑奖，2已兑奖，3已发货， 4已过期
     */
    private Integer status;
    /**
     * 是否使用，1使用，0禁用
     */
    private Integer useflag;
    /**
     * 插入时间
     */
    @TableField("insert_time")
    private Date insertTime;
    /**
     * 插入用户id
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
    /**
     * 城市
     */
    private String city;
    /**
     * 邮编
     */
    @TableField("post_code")
    private String postCode;
    /**
     * 省份
     */
    private String province;


    public Integer getId() {
        return id;
    }

    public PrizeLog setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getSrcId() {
        return srcId;
    }

    public PrizeLog setSrcId(String srcId) {
        this.srcId = srcId;
        return this;
    }

    public Integer getPrizeId() {
        return prizeId;
    }

    public PrizeLog setPrizeId(Integer prizeId) {
        this.prizeId = prizeId;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public PrizeLog setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public PrizeLog setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PrizeLog setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAdname() {
        return adname;
    }

    public PrizeLog setAdname(String adname) {
        this.adname = adname;
        return this;
    }

    public String getAdphone() {
        return adphone;
    }

    public PrizeLog setAdphone(String adphone) {
        this.adphone = adphone;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public PrizeLog setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public PrizeLog setUseflag(Integer useflag) {
        this.useflag = useflag;
        return this;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public PrizeLog setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public Integer getInsertId() {
        return insertId;
    }

    public PrizeLog setInsertId(Integer insertId) {
        this.insertId = insertId;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public PrizeLog setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public PrizeLog setUpdateId(Integer updateId) {
        this.updateId = updateId;
        return this;
    }

    public String getCity() {
        return city;
    }

    public PrizeLog setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public PrizeLog setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public PrizeLog setProvince(String province) {
        this.province = province;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PrizeLog{" +
        "id=" + id +
        ", srcId=" + srcId +
        ", prizeId=" + prizeId +
        ", price=" + price +
        ", buyerId=" + buyerId +
        ", address=" + address +
        ", adname=" + adname +
        ", adphone=" + adphone +
        ", status=" + status +
        ", useflag=" + useflag +
        ", insertTime=" + insertTime +
        ", insertId=" + insertId +
        ", updateTime=" + updateTime +
        ", updateId=" + updateId +
        ", city=" + city +
        ", postCode=" + postCode +
        ", province=" + province +
        "}";
    }
}
