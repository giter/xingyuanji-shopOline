package com.shopoline.xingyuanji.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shopoline.xingyuanji.entity.AdminInfo;
import com.shopoline.xingyuanji.model.UserShopLogInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuty
 * @since 2019-03-13
 */
public interface AdminInfoMapper extends BaseMapper<AdminInfo> {

    /**
     *  获取用户购买记录
     * @param openId
     * @return
     */
    List<UserShopLogInfoModel> getUserShopLogInfo(@Param("openId") String openId,@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);

    /**
     * 获取全部用户购买记录
     * @return
     */
    List<UserShopLogInfoModel> getAllShopLog(@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize,@Param("days")String days,
                                             @Param("nickName")String nickName,@Param("openId")String openId);


}
