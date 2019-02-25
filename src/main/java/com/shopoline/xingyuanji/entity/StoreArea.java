package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuty
 * @since 2019-01-17
 */
@TableName("store_area")
public class StoreArea extends Model<StoreArea> {

    private static final long serialVersionUID = 1L;

    private String code;
    private String province;
    private String city;
    private String area;
    private String town;


    public String getCode() {
        return code;
    }

    public StoreArea setCode(String code) {
        this.code = code;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public StoreArea setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public StoreArea setCity(String city) {
        this.city = city;
        return this;
    }

    public String getArea() {
        return area;
    }

    public StoreArea setArea(String area) {
        this.area = area;
        return this;
    }

    public String getTown() {
        return town;
    }

    public StoreArea setTown(String town) {
        this.town = town;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "StoreArea{" +
        "code=" + code +
        ", province=" + province +
        ", city=" + city +
        ", area=" + area +
        ", town=" + town +
        "}";
    }
}
