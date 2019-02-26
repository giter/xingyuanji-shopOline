package com.shopoline.xingyuanji.common;

public enum ExceptionEnum {

    EXCEPTION_1(1,"系统异常!"),
    EXCEPTION_2(2,"当前账号已经在别处登陆!"),
    EXCEPTION_3(3,"猩币不足"),
    EXCEPTION_4(4,"盒子卖完了呦"),
    EXCEPTION_5(5,"猩币还差那么一丢丢呦"),
    EXCEPTION_6(6,"下单失败了呦"),
    EXCEPTION_7(7,"下单失败了哦"),
    EXCEPTION_8(8,"请填写详细地址"),
    EXCEPTION_9(9,"请填写所在区域"),
    EXCEPTION_10(10,"请填写所在城市"),
    EXCEPTION_11(11,"请填写所在省份"),
    EXCEPTION_12(12,"请填写收货人姓名"),
    EXCEPTION_13(13,"请填写手机号码"),
    EXCEPTION_14(14,"顺丰请求失败"),
    EXCEPTION_15(15,"猩币低于最低抵扣数量"),
    EXCEPTION_16(16,"请输入地址"),
    EXCEPTION_17(17,"请勿重复操作"),
    EXCEPTION_18(18,"暂未查询到物流信息"),
    EXCEPTION_19(19,"商品已售罄"),
    EXCEPTION_20(20,"请勿删除最后默认地址"),
    EXCEPTION_21(21,"请输入11位手机号");

    public Integer key;
    public String desc;

    ExceptionEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * 按照Value获得枚举值
     */
    public static ExceptionEnum valueOf(Integer key) {
        if (key != null) {
            for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
                if (exceptionEnum.getKey() == key) {
                    return exceptionEnum;
                }
            }
        }
        return null;
    }

    //根据key获取value的值
    public static String getValueByKey(int key){
        for (ExceptionEnum s : ExceptionEnum.values()) {
            if(s.getKey()==key){
                return s.getDesc();
            }
        }
        return "";
    }

    //根据匹配value的值获取key
    public static int getKeyByValue(String channelName){
        for (ExceptionEnum s : ExceptionEnum.values()) {
            if(s.getDesc().equals(channelName)){
                return s.getKey();
            }
        }
        return 1;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
