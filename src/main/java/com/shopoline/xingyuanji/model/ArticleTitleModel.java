package com.shopoline.xingyuanji.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "文章标题对象")
public class ArticleTitleModel {

    @ApiModelProperty(value = "文章id",required = false)
    private String id;

    @ApiModelProperty(value = "文章标题",required = false)
    private String title;

    @ApiModelProperty(value = "操作时间",required = false)
    private String editTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }
}
