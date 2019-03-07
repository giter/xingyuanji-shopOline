package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
public class Address extends Model<Address> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 购买人id
     */
    @TableField("buyer_id")
    private Integer buyerId;
    /**
     * 邮编
     */
    @TableField("post_code")
    private String postCode;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 收货人姓名
     */
    private String name;
    /**
     * 收货人电话号码
     */
    private String phone;
    /**
     * 是否是默认收货地址，1是，0否
     */
    private Integer def;
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


    public Integer getId() {
        return id;
    }

    public Address setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public Address setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public Address setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public Address setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Address setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getName() {
        return name;
    }

    public Address setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Address setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Integer getDef() {
        return def;
    }

    public Address setDef(Integer def) {
        this.def = def;
        return this;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public Address setUseflag(Integer useflag) {
        this.useflag = useflag;
        return this;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public Address setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public Integer getInsertId() {
        return insertId;
    }

    public Address setInsertId(Integer insertId) {
        this.insertId = insertId;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Address setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public Address setUpdateId(Integer updateId) {
        this.updateId = updateId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Address{" +
        "id=" + id +
        ", buyerId=" + buyerId +
        ", postCode=" + postCode +
        ", province=" + province +
        ", city=" + city +
        ", address=" + address +
        ", name=" + name +
        ", phone=" + phone +
        ", def=" + def +
        ", useflag=" + useflag +
        ", insertTime=" + insertTime +
        ", insertId=" + insertId +
        ", updateTime=" + updateTime +
        ", updateId=" + updateId +
        "}";
    }
}
