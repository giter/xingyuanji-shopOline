package com.shopoline.xingyuanji.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.shopoline.xingyuanji.entity.ShopLog;
import com.shopoline.xingyuanji.model.PayModel;
import com.shopoline.xingyuanji.model.ShopLogModel;
import com.shopoline.xingyuanji.vo.LogisticInformationVO;
import com.shopoline.xingyuanji.vo.ShopLogInfoVO;

import java.util.List;

/**
 * <p>
 *  购物记录，业务相关接口
 * </p>
 *
 * @author wuty
 * @since 2019-01-10
 */
public interface IShopLogService extends IService<ShopLog> {

    /**
     * 统一下单
     * @param
     * @return
     */
    Object setOrder(String ticketId, PayModel payModel) throws Exception;

    /**
     * 交易成功后业务
     * @param ticketId
     * @param useXingBi 使用猩币数量
     * @return
     */
    Object afterPaySuccess(String ticketId,String useXingBi,String isPay,String randomToken,String UUID) throws Exception;

    /**
     * 卖了换币
     * @param shopLog
     */
    void updateExchangeCoinInfo(ShopLog shopLog);

    /**
     * 获取订单记录基本信息
     * @param ticketId
     * @return
     */
    List<ShopLogModel> getShopLog(String ticketId);

    /**
     * 查看订单记录商品详情
     * @param ticketId
     * @return
     */
    ShopLogInfoVO getShopLogInfo(String ticketId);

    /**
     * 寄回家（顺丰）
     * @param ticketId
     * @return
     */
    void sendHome(String ticketId, String productId) throws Exception;

    /**
     * 取消订单
     * @param ticketId
     * @return
     */
    Object deleteOrder(String ticketId,String orderId);

    /**
     * 获取运费
     * @param ticketId
     * @return
     */
    Object getZIPAmount(String ticketId);

    /**
     *
     * 够没积分奖品
     * @param ticketId
     * @param productId
     * @return
     */
    Object buyXingBiProduct(String ticketId, String productId) throws Exception;

    /**
     * 扣除猩币
     * @param ticketId
     * @return
     */
    void deductXingBi(String ticketId);

    /**
     * 查询当天线上销售数量
     * @return
     */
    Integer selectTodaySellCount();

    /**
     * 查询物流信息
     * @param ticketId
     * @param productId
     * @return
     */
    LogisticInformationVO getLogisticInformation(String ticketId, String productId, String tradeNo) throws Exception;

}
