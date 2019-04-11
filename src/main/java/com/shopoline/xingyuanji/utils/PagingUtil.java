package com.shopoline.xingyuanji.utils;

import com.shopoline.xingyuanji.model.PagingModel;

public class PagingUtil {


    public static PagingModel getPageInfo(Integer pageNum,Integer pageSize){

        // 根据页码计算查询条数
        Integer pageStart = (pageNum - 1) * pageSize;

        PagingModel pagingModel = new PagingModel();
        pagingModel.setPageSize(pageSize);
        pagingModel.setPageStart(pageStart);

        return pagingModel;
    }


}
