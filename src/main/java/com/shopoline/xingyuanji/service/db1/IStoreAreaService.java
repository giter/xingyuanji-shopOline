package com.shopoline.xingyuanji.service.db1;

import com.shopoline.xingyuanji.entity.StoreArea;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuty
 * @since 2019-01-17
 */
public interface IStoreAreaService extends IService<StoreArea> {

    StoreArea getStoreArea(String city);

}
