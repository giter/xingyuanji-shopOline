package com.shopoline.xingyuanji.mapper;

import com.shopoline.xingyuanji.entity.ShopLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shopoline.xingyuanji.model.ShopLogModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuty
 * @since 2019-01-10
 */
public interface ShopLogMapper extends BaseMapper<ShopLog> {

    /**
     * 获取用户购物记录
     * @param openId
     * @return
     */
    List<ShopLogModel> getShopList(@Param("openId") String openId);

    /**
     * 获取当天销售总数
     * @return
     */
    Integer selectTodaySellCount(@Param("boxIdBox") String boxIdBox,@Param("ispay") String ispay);

}
