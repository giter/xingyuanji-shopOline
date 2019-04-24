package com.shopoline.xingyuanji.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户地址对象")
public class UserAddressModel {

    @ApiModelProperty(value = "姓名",required = false)
    private String name;

    @ApiModelProperty(value = "电话",required = false)
    private String phone;

    @ApiModelProperty(value = "省",required = false)
    private String province;

    @ApiModelProperty(value = "市",required = false)
    private String city;

    @ApiModelProperty(value = "地址",required = false)
    private String address;

    @ApiModelProperty(value = "区",required = false)
    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
