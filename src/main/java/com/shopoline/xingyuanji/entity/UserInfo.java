package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息

 * </p>
 *
 * @author wuty
 * @since 2019-01-09
 */
@TableName("t_user_info")
public class UserInfo extends Model<UserInfo> {

    private static final long serialVersionUID = 1L;

    private String userId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 微信openId
     */
    private String openId;
    /**
     * 性别，1男2女
     */
    private Integer sex;
    /**
     * 头像链接
     */
    private String headImgUrl;
    /**
     * 插入时间
     */
    private Date editTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 操作人
     */
    private String editBy;
    /**
     * 0：禁用 1：启用
     */
    private Integer deleteFlag;


    public String getUserId() {
        return userId;
    }

    public UserInfo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public UserInfo setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public UserInfo setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public UserInfo setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public UserInfo setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
        return this;
    }

    public Date getEditTime() {
        return editTime;
    }

    public UserInfo setEditTime(Date editTime) {
        this.editTime = editTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public UserInfo setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public UserInfo setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public UserInfo setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
        "userId=" + userId +
        ", nickName=" + nickName +
        ", openId=" + openId +
        ", sex=" + sex +
        ", headImgUrl=" + headImgUrl +
        ", editTime=" + editTime +
        ", updateTime=" + updateTime +
        ", editBy=" + editBy +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
