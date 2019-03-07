package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 后端权限
     */
    private String permission;
    /**
     * 权限描述
     */
    private String description;
    /**
     * 前端路由url
     */
    @TableField("front_url")
    private String frontUrl;
    /**
     * 父权限id
     */
    @TableField("sup_id")
    private Integer supId;
    /**
     * 权限层级
     */
    private Integer level;
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

    public Permission setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getPermission() {
        return permission;
    }

    public Permission setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Permission setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getFrontUrl() {
        return frontUrl;
    }

    public Permission setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
        return this;
    }

    public Integer getSupId() {
        return supId;
    }

    public Permission setSupId(Integer supId) {
        this.supId = supId;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public Permission setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public Permission setUseflag(Integer useflag) {
        this.useflag = useflag;
        return this;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public Permission setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public Integer getInsertId() {
        return insertId;
    }

    public Permission setInsertId(Integer insertId) {
        this.insertId = insertId;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Permission setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public Permission setUpdateId(Integer updateId) {
        this.updateId = updateId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Permission{" +
        "id=" + id +
        ", permission=" + permission +
        ", description=" + description +
        ", frontUrl=" + frontUrl +
        ", supId=" + supId +
        ", level=" + level +
        ", useflag=" + useflag +
        ", insertTime=" + insertTime +
        ", insertId=" + insertId +
        ", updateTime=" + updateTime +
        ", updateId=" + updateId +
        "}";
    }
}
