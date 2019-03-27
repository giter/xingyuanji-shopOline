package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuty
 * @since 2019-03-13
 */
@TableName("t_admin_info")
public class AdminInfo extends Model<AdminInfo>{

    private static final long serialVersionUID = 1L;

    @TableField(value = "id")
    private String id;
    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;
    /**
     * 账号
     */
    @TableField(value = "userName")
    private String userName;
    /**
     * 密码
     */
    @TableField(value = "passWord")
    private String passWord;
    /**
     * 操作人
     */
    @TableField(value = "editBy")
    private String editBy;
    /**
     * 操作时间
     */
    @TableField(value = "editTime")
    private Date editTime;
    /**
     * 状态 0：禁用 1；启用
     */
    @TableField(value = "deleteFlag")
    private String deleteFlag;
    /**
     * 职位
     */
    @TableField(value = "position")
    private String position;


    public String getId() {
        return id;
    }

    public AdminInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdminInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public AdminInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassWord() {
        return passWord;
    }

    public AdminInfo setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public AdminInfo setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public Date getEditTime() {
        return editTime;
    }

    public AdminInfo setEditTime(Date editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public AdminInfo setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public AdminInfo setPosition(String position) {
        this.position = position;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
        "id=" + id +
        ", name=" + name +
        ", userName=" + userName +
        ", passWord=" + passWord +
        ", editBy=" + editBy +
        ", editTime=" + editTime +
        ", deleteFlag=" + deleteFlag +
        ", position=" + position +
        "}";
    }
}
