package com.shopoline.xingyuanji.service.db2;

import com.shopoline.xingyuanji.entity.Buyer;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
public interface IBuyerService extends IService<Buyer> {

    /**
     * 获取DB2用户信息
     * @param ticketId
     * @return
     */
    Buyer getDB2UserInfo(String ticketId);
}
