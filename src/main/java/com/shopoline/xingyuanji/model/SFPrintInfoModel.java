package com.shopoline.xingyuanji.model;

public class SFPrintInfoModel {


    //发货地编号
    private String sourceCityCode;
    //收货地编号
    private String destCityCode;
    //收货地地网点代码
    private String destDeptCode;
    //目的地网点代码映射码
    private String destDeptCodeMapping;

    private String destRouteLable;

    private String sourceTeamCode;
    //出港映射
    private String codingMappingOut;
    //打单时的路由标签信息
    private String destRouteLabel;
    //产品名称
    private String proName;
    //快件内容
    private String cargoTypeCode;
    //时效代码
    private String limitTypeCode;
    //产品类型,如
    private String expressTypeCode;
    //入港映射码
    private String codingMapping;
    //XB标志
    private String xbFlag;
    //二维码
    private String twoDimensionCode;
    //时效类型:
    private String proCode;
    //运费
    private Integer ZIPAmount;

    public String getCodingMappingOut() {
        return codingMappingOut;
    }

    public void setCodingMappingOut(String codingMappingOut) {
        this.codingMappingOut = codingMappingOut;
    }

    public Integer getZIPAmount() {
        return ZIPAmount;
    }

    public void setZIPAmount(Integer ZIPAmount) {
        this.ZIPAmount = ZIPAmount;
    }

    public String getSourceCityCode() {
        return sourceCityCode;
    }

    public void setSourceCityCode(String sourceCityCode) {
        this.sourceCityCode = sourceCityCode;
    }

    public String getDestCityCode() {
        return destCityCode;
    }

    public void setDestCityCode(String destCityCode) {
        this.destCityCode = destCityCode;
    }

    public String getDestDeptCode() {
        return destDeptCode;
    }

    public void setDestDeptCode(String destDeptCode) {
        this.destDeptCode = destDeptCode;
    }

    public String getDestDeptCodeMapping() {
        return destDeptCodeMapping;
    }

    public void setDestDeptCodeMapping(String destDeptCodeMapping) {
        this.destDeptCodeMapping = destDeptCodeMapping;
    }


    public String getDestRouteLabel() {
        return destRouteLabel;
    }

    public void setDestRouteLabel(String destRouteLabel) {
        this.destRouteLabel = destRouteLabel;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getCargoTypeCode() {
        return cargoTypeCode;
    }

    public void setCargoTypeCode(String cargoTypeCode) {
        this.cargoTypeCode = cargoTypeCode;
    }

    public String getLimitTypeCode() {
        return limitTypeCode;
    }

    public void setLimitTypeCode(String limitTypeCode) {
        this.limitTypeCode = limitTypeCode;
    }

    public String getExpressTypeCode() {
        return expressTypeCode;
    }

    public void setExpressTypeCode(String expressTypeCode) {
        this.expressTypeCode = expressTypeCode;
    }

    public String getCodingMapping() {
        return codingMapping;
    }

    public void setCodingMapping(String codingMapping) {
        this.codingMapping = codingMapping;
    }

    public String getXbFlag() {
        return xbFlag;
    }

    public void setXbFlag(String xbFlag) {
        this.xbFlag = xbFlag;
    }

    public String getTwoDimensionCode() {
        return twoDimensionCode;
    }

    public void setTwoDimensionCode(String twoDimensionCode) {
        this.twoDimensionCode = twoDimensionCode;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }
}
