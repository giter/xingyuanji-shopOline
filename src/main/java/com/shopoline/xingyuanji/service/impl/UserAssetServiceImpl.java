package com.shopoline.xingyuanji.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.shopoline.xingyuanji.Constants;
import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.entity.ProductInfo;
import com.shopoline.xingyuanji.entity.ShopLog;
import com.shopoline.xingyuanji.entity.UserAsset;
import com.shopoline.xingyuanji.entity.UserInfo;
import com.shopoline.xingyuanji.mapper.UserAssetMapper;
import com.shopoline.xingyuanji.service.IProductInfoService;
import com.shopoline.xingyuanji.service.IShopLogService;
import com.shopoline.xingyuanji.service.IUserAssetService;
import com.shopoline.xingyuanji.service.IUserInfoService;
import com.shopoline.xingyuanji.utils.GetOpenId;
import com.shopoline.xingyuanji.vo.UserCoinVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * <p>
 * 用户资产表
 服务实现类
 * </p>
 *
 * @author wuty
 * @since 2019-01-09
 */
@Service
@Transactional
public class UserAssetServiceImpl extends ServiceImpl<UserAssetMapper, UserAsset> implements IUserAssetService {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private IProductInfoService productInfoService;
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IShopLogService shopLogService;

    /**
     * 查询用户资产表猩币数量
     */
    @Override
    public UserCoinVO quertUserCoin(String ticketId) {

        UserCoinVO userCoinVO = new UserCoinVO();
        String openId = GetOpenId.getOpenId(ticketId);
        Assert.isTrue(openId != null, ExceptionEnum.EXCEPTION_1.getDesc());
        UserAsset asset = baseMapper.sumAmountById(openId, Constants.XINGBI);
        if(asset == null){
            userCoinVO.setAmount(0);
        }else {
            userCoinVO.setAmount(asset.getAmount());
        }
        userCoinVO.setAmountType(Constants.XINGBI);
        return userCoinVO;
    }

    /**
     * 如果用猩币，扣除猩币
     * @param userInfo
     */
    @Override
    @Transactional
    public void setUseXingBi(UserInfo userInfo) {
        UserAsset userAsset = baseMapper.selectUserXingBi(Constants.XINGBI,userInfo.getOpenId());
        if (userAsset != null){
            UserAsset userAsset1 = new UserAsset();
            userAsset1.setId(IdWorker.get32UUID());
            userAsset1.setUserId(userInfo.getUserId());
            userAsset1.setAmount(-50);
            userAsset1.setAmountTye(Constants.XINGBI);
            userAsset1.setDeltFlag(Constants.QIYONG);
            userAsset1.setEditTime(new Date());
            userAsset1.setEditBy("admin");
            userAsset1.setOpenId(userInfo.getOpenId());
            this.insert(userAsset1);
        }
    }

    /**
     * 卖了换币
     * @param ticketId
     * @param goodsId
     * @return
     */
    @Override
    public Object exchangeCoin(String ticketId, String goodsId) {

        String openId = GetOpenId.getOpenId(ticketId);
        UserInfo userInfo = userInfoService.selectOne(new EntityWrapper<UserInfo>().eq("openId",openId).eq("deleteFlag",Constants.QIYONG));
        //查询商品信息
        ProductInfo productInfo = productInfoService.selectOne(new EntityWrapper<ProductInfo>().eq("id",goodsId).eq("style",Constants.XINGBI).
                eq("kind",Constants.XINGBI).eq("deleteFlag",Constants.QIYONG));
        //将商品积分写入用户财富表
        UserAsset userAsset = new UserAsset();
        userAsset.setId(IdWorker.get32UUID());
        userAsset.setUserId(userInfo.getUserId());
        userAsset.setAmount(Integer.valueOf(productInfo.getSocer()));
        userAsset.setAmountTye(Constants.XINGBI);
        userAsset.setEditTime(new Date());
        userAsset.setEditBy("admin");
        userAsset.setDeltFlag(Constants.QIYONG);
        userAsset.setOpenId(openId);
        this.insert(userAsset);
        //更改用户购买商品信息
        ShopLog shopLog = shopLogService.selectOne(new EntityWrapper<ShopLog>().eq("goodsId",goodsId).
                eq("openId",openId).eq("deleteFlag",Constants.QIYONG));
        shopLog.setExpress(Constants.MALEHUANBI);
        shopLog.setEditTime(new Date());
        shopLogService.updateExchangeCoinInfo(shopLog);
//        //返回积分商品列表
//        List<ProductInfo> productInfoList = productInfoService.selectList(new EntityWrapper<ProductInfo>().
//                eq("style",Constants.JIFEN_PRODUCT).eq("kind",Constants.JIFEN_PRODUCT).eq("deleteFlag",Constants.QIYONG));

        logger.info("EXCHANGE_COIN-\t"+"UserName："+userInfo.getNickName()+"\tSelledProductName："+productInfo.getGoodsname()+
                "\tSelledProductId："+productInfo.getId()+"\tAmount："+userAsset.getAmount()+"\tDate："+new Date());

        return productInfo.getSocer();
    }

    /**
     * 扣除用户猩币
     * @param productInfo
     * @param userInfo
     */
    @Override
    @Transactional
    public void dedUserXingbi(ProductInfo productInfo, UserInfo userInfo) {

        UserAsset userAsset = new UserAsset();
        userAsset.setId(IdWorker.get32UUID());
        userAsset.setUserId(userInfo.getUserId());
        userAsset.setAmount(- Integer.parseInt(productInfo.getSocer()));
        userAsset.setAmountTye(Constants.XINGBI);
        userAsset.setEditTime(new Date());
        userAsset.setEditBy("admin");
        userAsset.setDeltFlag(Constants.QIYONG);
        userAsset.setOpenId(userInfo.getOpenId());
        this.insert(userAsset);

        logger.info("DELETE_USER_AMOUNT-\t"+"UserName："+userInfo.getNickName()+"\tSelledProductName："+productInfo.getGoodsname()+
                "\tSelledProductId："+productInfo.getId()+"\tAmount："+userAsset.getAmount()+"\tDate："+new Date());

    }

}
