package com.shopoline.xingyuanji.service.db1.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.shopoline.xingyuanji.Constants;
import com.shopoline.xingyuanji.entity.WebsiteProduct;
import com.shopoline.xingyuanji.mapper.WebsiteProductMapper;
import com.shopoline.xingyuanji.model.WebsiteProductModel;
import com.shopoline.xingyuanji.service.db1.IWebsiteProductService;
import com.shopoline.xingyuanji.vo.WebsiteProductInfoVO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuty
 * @since 2019-04-04
 */
@Service
public class WebsiteProductServiceImpl extends ServiceImpl<WebsiteProductMapper, WebsiteProduct> implements IWebsiteProductService   {


    /**
     * 获取官网产品信息
     * @param style
     * @param pageNum
     * @return
     */
    @Override
    public WebsiteProductInfoVO getProductInfoByStyle(String style, String pageNum) {

        // 每页记录数量
        Integer pageSize = 6;
        // 根据页码计算查询条数
        Integer pageStart = (Integer.valueOf(pageNum) - 1) * pageSize;

        List<WebsiteProduct> websiteProductList = baseMapper.getProductInfoByStyle(style,pageStart,pageSize);
        ListIterator<WebsiteProduct> iterator = websiteProductList.listIterator();
        while (iterator.hasNext()){
            WebsiteProduct websiteProduct= iterator.next();
            websiteProduct.setImg("\\websiteProduct\\"+websiteProduct.getImg());
        }
        WebsiteProductInfoVO websiteProductInfoVO=new WebsiteProductInfoVO();
        websiteProductInfoVO.setWebsiteProductList(websiteProductList);
        websiteProductInfoVO.setPagrCount(this.selectCount(new EntityWrapper<WebsiteProduct>().eq("style",style).
                eq("deleteFlag", Constants.QIYONG)));
        return websiteProductInfoVO;
    }

    /**
     * 增加或新增官网商品
     * @param websiteProductModel
     */
    @Override
    public void insertWebSiteProduct(WebsiteProductModel websiteProductModel) {

        WebsiteProduct websiteProduct = this.selectOne(new EntityWrapper<WebsiteProduct>().eq("id", websiteProductModel.getId()).last("LIMIT 1"));

        if (websiteProduct == null) {
            WebsiteProduct product = new WebsiteProduct();
            product.setId(IdWorker.get32UUID());
            product.setProductName(websiteProductModel.getProductName());
            product.setStyle(websiteProductModel.getStyle());
            product.setEditTime(new Date());
            product.setEditBy(Constants.ADMIN);
            product.setDeleteFlag(websiteProductModel.getDeleteFlag());
            this.insert(product);
        } else {
            if (websiteProductModel.getProductName() != null) {
                websiteProduct.setProductName(websiteProductModel.getProductName());
            }
            if (websiteProductModel.getStyle() != null) {
                websiteProduct.setStyle(websiteProductModel.getStyle());
            }
            if (websiteProductModel.getDeleteFlag() != null) {
                websiteProduct.setDeleteFlag(websiteProductModel.getDeleteFlag());
            }
            websiteProduct.setEditTime(new Date());
            this.updateById(websiteProduct);
        }

    }


}
