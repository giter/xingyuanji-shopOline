package com.shopoline.xingyuanji.service;

import com.baomidou.mybatisplus.service.IService;
import com.shopoline.xingyuanji.entity.ProductInfo;
import com.shopoline.xingyuanji.vo.ProductInfoVO;

import java.util.List;

/**
 * <p>
 *  商品信息相关接口
 * </p>
 *
 * @author wuty
 * @since 2019-01-09
 */
public interface IProductInfoService extends IService<ProductInfo> {

    /**
     * 获取盒子信息
     * @return
     */
    ProductInfoVO getBoxInfo(String type, String kind);

    /**
     * 获取盒子抵扣价格
     * @return
     */
    Float getBoxDeductionPrice(String ticketId,String type, String kind,Integer boxCount);

    /**
     * 购盒成功后获取随机商品
     * @param productStyle 0：盒子商品 1：积分商品
     * @param productKind 0：盒子商品 1：积分商品
     * @return
     */
    ProductInfo getRedomProduct(String ticketId,Integer productStyle,Integer productKind,String randomToken,String UUID) throws Exception;

    /**
     * 获取积分商城列表
     *
     * @return
     */
    List<ProductInfo> getShopList();

    /**
     * 获取商城商品详情
     * @param productId
     * @return
     */
    ProductInfo getShopProductInfo(String productId) throws Exception;

    /**
     * 获取盒子对应图片
     * @return
     */
    Object getBoxImg(String boxId);

}
