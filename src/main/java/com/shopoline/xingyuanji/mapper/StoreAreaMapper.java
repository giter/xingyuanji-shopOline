package com.shopoline.xingyuanji.mapper;

import com.shopoline.xingyuanji.entity.StoreArea;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuty
 * @since 2019-01-17
 */
public interface StoreAreaMapper extends BaseMapper<StoreArea> {

    StoreArea getStoreArea(@Param("city") String city);

}
