package com.shopoline.xingyuanji.mapper;

import com.shopoline.xingyuanji.entity.WebsiteProduct;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuty
 * @since 2019-04-04
 */
public interface WebsiteProductMapper extends BaseMapper<WebsiteProduct> {

    /**
     * 获取官网商品详情
     * @param style
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<WebsiteProduct> getProductInfoByStyle(@Param("style") String style,@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);


}
