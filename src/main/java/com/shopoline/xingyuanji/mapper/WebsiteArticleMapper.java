package com.shopoline.xingyuanji.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shopoline.xingyuanji.entity.WebsiteArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuty
 * @since 2019-04-08
 */
public interface WebsiteArticleMapper extends BaseMapper<WebsiteArticle> {

    /**
     * 获取文章列表
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<WebsiteArticle> getArticleList(@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);
}
