package com.shopoline.xingyuanji.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 奖品二维码表
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
@TableName("prize_code")
public class PrizeCode extends Model<PrizeCode> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 抽奖二维码id
     */
    private String uid;
    /**
     * 是否使用，0未使用，1已使用
     */
    private Integer status;
    /**
     * 二维码地址
     */
    private String img;
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

    public PrizeCode setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public PrizeCode setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public PrizeCode setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getImg() {
        return img;
    }

    public PrizeCode setImg(String img) {
        this.img = img;
        return this;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public PrizeCode setUseflag(Integer useflag) {
        this.useflag = useflag;
        return this;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public PrizeCode setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public Integer getInsertId() {
        return insertId;
    }

    public PrizeCode setInsertId(Integer insertId) {
        this.insertId = insertId;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public PrizeCode setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public PrizeCode setUpdateId(Integer updateId) {
        this.updateId = updateId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "PrizeCode{" +
        "id=" + id +
        ", uid=" + uid +
        ", status=" + status +
        ", img=" + img +
        ", useflag=" + useflag +
        ", insertTime=" + insertTime +
        ", insertId=" + insertId +
        ", updateTime=" + updateTime +
        ", updateId=" + updateId +
        "}";
    }
}
