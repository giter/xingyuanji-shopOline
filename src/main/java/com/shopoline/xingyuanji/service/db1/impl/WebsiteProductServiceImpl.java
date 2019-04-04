package com.shopoline.xingyuanji.service.db1.impl;

import com.shopoline.xingyuanji.entity.WebsiteProduct;
import com.shopoline.xingyuanji.mapper.WebsiteProductMapper;
import com.shopoline.xingyuanji.service.db1.IWebsiteProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shopoline.xingyuanji.vo.WebsiteProductInfoVO;
import org.springframework.stereotype.Service;

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
public class WebsiteProductServiceImpl extends ServiceImpl<WebsiteProductMapper, WebsiteProduct> implements IWebsiteProductService {


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

        return websiteProductInfoVO;
    }
}
