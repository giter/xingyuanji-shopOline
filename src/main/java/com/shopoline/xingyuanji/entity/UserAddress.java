package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户邮寄地址
 * </p>
 *
 * @author wuty
 * @since 2019-01-16
 */
@TableName("t_user_address")
public class UserAddress extends Model<UserAddress> {

    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 电话
     */
    private String phone;
    /**
     * 省
     */
    private String province;
    /**
     * 市区
     */
    private String city;
    /**
     * 地址
     */
    private String address;
    /**
     * 是否默认 0：否 1：是
     */
    private Integer def;
    /**
     * 操作时间
     */
    private Date editTime;
    /**
     * 操作人
     */
    private String editBy;
    /**
     * 是否启用 0：否 1：是
     */
    private Integer deleteFlag;
    /**
     * 区
     */
    private String area;


    public String getId() {
        return id;
    }

    public UserAddress setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserAddress setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserAddress setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public UserAddress setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserAddress setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getDef() {
        return def;
    }

    public UserAddress setDef(Integer def) {
        this.def = def;
        return this;
    }

    public Date getEditTime() {
        return editTime;
    }

    public UserAddress setEditTime(Date editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public UserAddress setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public UserAddress setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }

    public String getArea() {
        return area;
    }

    public UserAddress setArea(String area) {
        this.area = area;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
        "id=" + id +
        ", userId=" + userId +
        ", name=" + name +
        ", phone=" + phone +
        ", province=" + province +
        ", city=" + city +
        ", address=" + address +
        ", def=" + def +
        ", editTime=" + editTime +
        ", editBy=" + editBy +
        ", deleteFlag=" + deleteFlag +
        ", area=" + area +
        "}";
    }
}
