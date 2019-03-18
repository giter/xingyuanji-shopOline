package com.shopoline.xingyuanji.service.db1.impl;

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
import com.shopoline.xingyuanji.model.UserAssetInfoModel;
import com.shopoline.xingyuanji.service.db1.IProductInfoService;
import com.shopoline.xingyuanji.service.db1.IShopLogService;
import com.shopoline.xingyuanji.service.db1.IUserAssetService;
import com.shopoline.xingyuanji.service.db1.IUserInfoService;
import com.shopoline.xingyuanji.utils.GetOpenId;
import com.shopoline.xingyuanji.vo.UserCoinVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户资产服务实现类
 * </p>
 *
 * @author wuty
 * @since 2019-01-09
 */
@Service
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
            userCoinVO.setAmount(Constants.NULL);
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
            userAsset1.setAmount(Constants.GOUHE_DIKOU);
            userAsset1.setAmountTye(Constants.XINGBI);
            userAsset1.setDeltFlag(Constants.QIYONG);
            userAsset1.setEditTime(new Date());
            userAsset1.setEditBy(Constants.ADMIN);
            userAsset1.setOpenId(userInfo.getOpenId());
            logger.info("<-DELETE_USER_XINGBI->\t"+"NickName："+userInfo.getNickName()+"\tOpenId："+userInfo.getOpenId()+
                    "\tDELETE_XINGBI_AMOUNT："+userAsset1.getAmount()+"\tAMOUNT_TYPE：猩币"+"DATE："+userAsset1.getEditTime());
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
    @Transactional
    public Object exchangeCoin(String ticketId, String goodsId) {


        UserInfo userInfo = userInfoService.getDB1UserInfo(ticketId);
        //查询商品信息
        ProductInfo productInfo = productInfoService.selectOne(new EntityWrapper<ProductInfo>().eq("id",goodsId).
                eq("style",Constants.XINGBI).eq("kind",Constants.XINGBI).
                eq("deleteFlag",Constants.QIYONG).last("Limit 1"));
        //将商品积分写入用户财富表
        UserAsset userAsset = new UserAsset();
        userAsset.setId(IdWorker.get32UUID());
        userAsset.setUserId(userInfo.getUserId());
        userAsset.setAmount(Integer.valueOf(productInfo.getSocer()));
        userAsset.setAmountTye(Constants.XINGBI);
        userAsset.setEditTime(new Date());
        userAsset.setEditBy(Constants.ADMIN);
        userAsset.setDeltFlag(Constants.QIYONG);
        // openId
        String openId = GetOpenId.getOpenId(ticketId);
        userAsset.setOpenId(openId);
        this.insert(userAsset);
        //更改用户购买商品信息
        ShopLog shopLog = shopLogService.selectOne(new EntityWrapper<ShopLog>().eq("goodsId",goodsId).
                eq("openId",openId).eq("deleteFlag",Constants.QIYONG).last("Limit 1"));
        shopLog.setExpress(Constants.MALEHUANBI);
        shopLog.setUpdateTime(new Date());
        shopLogService.updateExchangeCoinInfo(shopLog);

        logger.info("<-EXCHANGE_COIN->\t"+"UserName："+userInfo.getNickName()+"\tSelledProductName："+productInfo.getGoodsname()+
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
        userAsset.setEditBy(Constants.ADMIN);
        userAsset.setDeltFlag(Constants.QIYONG);
        userAsset.setOpenId(userInfo.getOpenId());
        // log
        logger.info("<-DELETE_USER_AMOUNT->\t"+"UserName："+userInfo.getNickName()+"\tOpenId："+userInfo.getOpenId()+
                "\tSelledProductName："+productInfo.getGoodsname()+"\tSelledProductId："+productInfo.getId()+"\tDELETE_TYPE：猩币"+"\tAmount："+
                userAsset.getAmount()+"\tDate："+userAsset.getEditTime());
        this.insert(userAsset);
    }

    /**
     * 获取用户资产信息列表
     * @param userId
     * @return
     */
    @Override
    public List<UserAssetInfoModel> getUserAssetInfoList(String userId,Integer pageStart,Integer pageSize) {
        return baseMapper.getUserAssetInfoList(userId,pageStart,pageSize);
    }

    /**
     * 获取用户资产总数
     * @param userId
     * @return
     */
    @Override
    public Integer getUserAssetCount(String userId) {
        return baseMapper.getUserAssetCount(userId);
    }

}
