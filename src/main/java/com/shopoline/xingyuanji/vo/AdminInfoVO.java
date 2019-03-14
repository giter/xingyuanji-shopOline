package com.shopoline.xingyuanji.vo;

public class AdminInfoVO {


    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 账号
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 操作人
     */
    private String editBy;
    /**
     * 操作时间
     */
    private String editTime;
    /**
     * 状态 0：禁用 1；启用
     */
    private String deleteFlag;
    /**
     * 职位
     */
    private String position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
