package com.shopoline.xingyuanji.utils;

import com.shopoline.xingyuanji.model.PagingModel;

public class PagingUtil {


    public static PagingModel getPageInfo(String pageNum){

        // 每页记录数量
        Integer pageSize = 6;
        // 根据页码计算查询条数
        Integer pageStart = (Integer.valueOf(pageNum) - 1) * pageSize;

        PagingModel pagingModel = new PagingModel();
        pagingModel.setPageSize(pageSize);
        pagingModel.setPageStart(pageStart);

        return pagingModel;
    }
}
