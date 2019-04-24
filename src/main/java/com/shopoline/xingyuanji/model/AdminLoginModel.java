package com.shopoline.xingyuanji.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

@ApiModel(value = "管理员登陆对象。无需JSON传值直接下方两个参数拼接",description ="无需JSON直接下方两个参数拼接" )
public class AdminLoginModel {

    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    @ApiModelProperty(value = "密码",required = true)
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "AdminLoginModel{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }



    public AdminLoginModel(String userName, String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }

    private boolean equalsStr(String str1, String str2){
        if(StringUtils.isEmpty(str1) && StringUtils.isEmpty(str2)){
            return true;
        }
        if(!StringUtils.isEmpty(str1) && str1.equals(str2)){
            return true;
        }
        return false;
    }

    // 重写equals比较两个Object内元素是否相等
    @Override
    public boolean equals(Object object) {
        if(this == object){
            // 地址相等
            return true;
        }
        if(object == null){
            // 非空性：对于任意非空引用x，x.equals(null)应该返回false。
            return false;
        }
        if(object instanceof AdminLoginModel){
            AdminLoginModel other = (AdminLoginModel) object;
            // 需要比较的字段相等，则这两个对象相等
            if(equalsStr(this.userName, other.userName) && equalsStr(this.passWord, other.passWord)){
                return true;
            }
        }
        return false;
    }

        // 重写HashCode
        @Override
        public int hashCode(){
            int result = 17;
            result = 31 * result + (userName == null ? 0 : userName.hashCode());
            result = 31 * result + (passWord == null ? 0 : passWord.hashCode());
            return result;
        }


}
