package com.shopoline.xingyuanji.mapper;

import com.shopoline.xingyuanji.entity.UserAddress;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shopoline.xingyuanji.model.UserAddressInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户邮寄地址 Mapper 接口
 * </p>
 *
 * @author wuty
 * @since 2019-01-09
 */
public interface UserAddressMapper extends BaseMapper<UserAddress> {


    /**
     * 获取用户地址信息(后台管理)
     * @param userId
     * @return
     */
    List<UserAddressInfoModel> getUserAddressInfoList(@Param("userId") String userId);
}
