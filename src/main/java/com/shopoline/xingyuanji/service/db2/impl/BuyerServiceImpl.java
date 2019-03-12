package com.shopoline.xingyuanji.service.db2.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shopoline.xingyuanji.entity.Buyer;
import com.shopoline.xingyuanji.mapper.BuyerMapper;
import com.shopoline.xingyuanji.service.db2.IBuyerService;
import com.shopoline.xingyuanji.utils.GetOpenId;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
@Service
public class BuyerServiceImpl extends ServiceImpl<BuyerMapper, Buyer> implements IBuyerService {

    /**
     * 获取DB2用户信息
     * @param ticketId
     * @return
     */
    @Override
    public Buyer getDB2UserInfo(String ticketId) {

        synchronized (this){
            String openId = GetOpenId.getOpenId(ticketId);
            // 获取用户信息
            Buyer buyer = this.selectOne(new EntityWrapper<Buyer>().eq("open_id",openId).last("Limit 1"));
            return buyer;
        }
    }
}
