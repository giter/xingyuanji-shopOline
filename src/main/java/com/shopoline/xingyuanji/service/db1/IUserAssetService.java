package com.shopoline.xingyuanji.service.db1;

import com.shopoline.xingyuanji.entity.ProductInfo;
import com.shopoline.xingyuanji.entity.UserAsset;
import com.baomidou.mybatisplus.service.IService;
import com.shopoline.xingyuanji.entity.UserInfo;
import com.shopoline.xingyuanji.vo.UserCoinVO;

/**
 * <p>
 * 用户资产相关接口
 * </p>
 *
 * @author wuty
 * @since 2019-01-09
 */
public interface IUserAssetService extends IService<UserAsset> {

    /**
     * 查询用户资产表猩币数量
     */
    UserCoinVO quertUserCoin(String ticketId);

    /**
     * 如果用猩币，扣除猩币
     * @param userInfo
     */
    void setUseXingBi(UserInfo userInfo);

    /**
     * 卖了换币
     * @param ticketId
     * @param goodsId
     * @return
     */
    Object exchangeCoin(String ticketId, String goodsId);

    /**
     * 扣除用户猩币
     * @param productInfo
     * @param userInfo
     */
    void dedUserXingbi(ProductInfo productInfo, UserInfo userInfo);

}
