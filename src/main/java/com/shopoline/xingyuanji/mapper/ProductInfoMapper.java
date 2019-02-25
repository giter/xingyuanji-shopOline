package com.shopoline.xingyuanji.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shopoline.xingyuanji.entity.ProductInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuty
 * @since 2019-01-09
 */
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {


    /**
     * 购盒成功后获取随机商品
     * @param productStyle 0：盒子商品 1：积分商品
     * @return
     */
    ProductInfo getRedomProduct(@Param("productStyle") Integer productStyle,@Param("productKind") Integer productKind);

}
