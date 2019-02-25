package com.shopoline.xingyuanji.service.impl;

import com.shopoline.xingyuanji.entity.StoreArea;
import com.shopoline.xingyuanji.mapper.StoreAreaMapper;
import com.shopoline.xingyuanji.service.IStoreAreaService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuty
 * @since 2019-01-17
 */
@Service
public class StoreAreaServiceImpl extends ServiceImpl<StoreAreaMapper, StoreArea> implements IStoreAreaService {

    @Override
    public StoreArea getStoreArea(String city) {
        return baseMapper.getStoreArea(city);
    }
}
