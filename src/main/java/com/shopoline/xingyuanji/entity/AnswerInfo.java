package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuty
 * @since 2019-01-10
 */
@TableName("t_answer_info")
public class AnswerInfo extends Model<AnswerInfo> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 用户微信openId
     */
    private String openId;
    /**
     * 问题
     */
    private String question;
    /**
     * 答案
     */
    private String answer;
    /**
     * 操作人
     */
    private String editBy;
    /**
     * 操作时间
     */
    private Date editTime;
    /**
     * 删除状态 0：未启用 1：已经启用
     */
    private Integer deleteFlag;


    public String getId() {
        return id;
    }

    public AnswerInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public AnswerInfo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public AnswerInfo setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public AnswerInfo setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public AnswerInfo setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public AnswerInfo setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public Date getEditTime() {
        return editTime;
    }

    public AnswerInfo setEditTime(Date editTime) {
        this.editTime = editTime;
        return this;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public AnswerInfo setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AnswerInfo{" +
        "id=" + id +
        ", userId=" + userId +
        ", openId=" + openId +
        ", question=" + question +
        ", answer=" + answer +
        ", editBy=" + editBy +
        ", editTime=" + editTime +
        ", deleteFlag=" + deleteFlag +
        "}";
    }
}
