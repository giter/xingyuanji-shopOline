package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 奖品信息表
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
public class Prize extends Model<Prize>{

    private static final long serialVersionUID = 1L;

    /**
     * 奖品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 奖品名称
     */
    private String name;
    /**
     * 奖品图片
     */
    private String pimg;
    /**
     * 奖品数量
     */
    private Integer num;
    /**
     * 兑奖开始日期
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 兑奖截止日期
     */
    @TableField("end_time")
    private Date endTime;
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

    public Prize setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Prize setName(String name) {
        this.name = name;
        return this;
    }

    public String getPimg() {
        return pimg;
    }

    public Prize setPimg(String pimg) {
        this.pimg = pimg;
        return this;
    }

    public Integer getNum() {
        return num;
    }

    public Prize setNum(Integer num) {
        this.num = num;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Prize setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Prize setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public Prize setUseflag(Integer useflag) {
        this.useflag = useflag;
        return this;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public Prize setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public Integer getInsertId() {
        return insertId;
    }

    public Prize setInsertId(Integer insertId) {
        this.insertId = insertId;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Prize setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public Prize setUpdateId(Integer updateId) {
        this.updateId = updateId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Prize{" +
        "id=" + id +
        ", name=" + name +
        ", pimg=" + pimg +
        ", num=" + num +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", useflag=" + useflag +
        ", insertTime=" + insertTime +
        ", insertId=" + insertId +
        ", updateTime=" + updateTime +
        ", updateId=" + updateId +
        "}";
    }
}
