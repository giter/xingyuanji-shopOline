package com.shopoline.xingyuanji.service.db2;

import com.baomidou.mybatisplus.service.IService;
import com.shopoline.xingyuanji.entity.Prize;
import com.shopoline.xingyuanji.model.PrizeModel;

import java.util.List;

/**
 * <p>
 * 奖品信息表 服务类
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
public interface IPrizeService extends IService<Prize> {

    /**
     * 获取用户奖品列表
     * @param ticketId
     * @return
     */
    List<PrizeModel> getPrizeList(String ticketId);


}
