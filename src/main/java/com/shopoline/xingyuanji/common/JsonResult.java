package com.shopoline.xingyuanji.common;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 7175453734840740290L;

    private static final int SUCCESS = 0;
    private static final int ERROR = 1;
    private static final String MESSAGE = "成功";

    private int state;
    private String message;
    private T data;
    private Long totalRecordNumber;

    //构造方法
    public JsonResult() {
        state = SUCCESS;
        message = MESSAGE;
    }

    public JsonResult(T data){
        state = SUCCESS;
        this.data = data;
    }

    public JsonResult(Throwable e){
        state = ERROR;
        this.message = e.getMessage();
    }

    //get AND set 方法
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTotalRecordNumber() {
        return totalRecordNumber;
    }

    public void setTotalRecordNumber(Long totalRecordNumber) {
        this.totalRecordNumber = totalRecordNumber;
    }

    @Override
    public String toString() {
        return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + ", totalRecordNumber" + totalRecordNumber + "]";
    }













}
