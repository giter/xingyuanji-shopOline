package com.shopoline.xingyuanji.service.db2;

import com.shopoline.xingyuanji.entity.PrizeLog;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 兑奖记录 服务类
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
public interface IPrizeLogService extends IService<PrizeLog> {

    void updateByInfo(PrizeLog prizeLog);

}
