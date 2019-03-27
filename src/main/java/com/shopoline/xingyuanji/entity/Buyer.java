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
public class Buyer extends Model<Buyer>{

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 微信openId
     */
    @TableField("open_id")
    private String openId;
    /**
     * 性别，1男2女
     */
    private Integer sex;
    /**
     * 头像链接
     */
    @TableField("avatar_url")
    private String avatarUrl;
    /**
     * 积分
     */
    private Integer points;
    /**
     * 点赞数量
     */
    private Integer zan;
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

    public Buyer setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public Buyer setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public Buyer setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public Buyer setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Buyer setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public Integer getPoints() {
        return points;
    }

    public Buyer setPoints(Integer points) {
        this.points = points;
        return this;
    }

    public Integer getZan() {
        return zan;
    }

    public Buyer setZan(Integer zan) {
        this.zan = zan;
        return this;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public Buyer setUseflag(Integer useflag) {
        this.useflag = useflag;
        return this;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public Buyer setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public Integer getInsertId() {
        return insertId;
    }

    public Buyer setInsertId(Integer insertId) {
        this.insertId = insertId;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Buyer setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public Buyer setUpdateId(Integer updateId) {
        this.updateId = updateId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Buyer{" +
        "id=" + id +
        ", nickName=" + nickName +
        ", openId=" + openId +
        ", sex=" + sex +
        ", avatarUrl=" + avatarUrl +
        ", points=" + points +
        ", zan=" + zan +
        ", useflag=" + useflag +
        ", insertTime=" + insertTime +
        ", insertId=" + insertId +
        ", updateTime=" + updateTime +
        ", updateId=" + updateId +
        "}";
    }
}
