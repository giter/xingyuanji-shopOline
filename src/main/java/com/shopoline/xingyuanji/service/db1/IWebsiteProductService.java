package com.shopoline.xingyuanji.service.db1;

import com.shopoline.xingyuanji.entity.WebsiteProduct;
import com.baomidou.mybatisplus.service.IService;
import com.shopoline.xingyuanji.model.WebsiteProductModel;
import com.shopoline.xingyuanji.vo.WebsiteProductInfoVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuty
 * @since 2019-04-04
 */
public interface IWebsiteProductService extends IService<WebsiteProduct> {

    /**
     * 获取官网产品信息
     * @param style
     * @param pageNum
     * @return
     */
    WebsiteProductInfoVO getProductInfoByStyle(String style, String pageNum);

    /**
     * 增加或新增官网商品
     * @param websiteProductModel
     */
    void insertWebSiteProduct(WebsiteProductModel websiteProductModel);
}
