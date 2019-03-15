package com.shopoline.xingyuanji.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shopoline.xingyuanji.entity.UserAsset;
import com.shopoline.xingyuanji.model.UserAssetInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户资产表
 Mapper 接口
 * </p>
 *
 * @author wuty
 * @since 2019-01-10
 */
public interface UserAssetMapper extends BaseMapper<UserAsset> {


    /**
     * 根据OpenId获取用户猩币数量
     * @param openId
     * @param xingbi
     * @return
     */
    UserAsset sumAmountById(@Param("openId") String openId,@Param("xingbi") Integer xingbi);

    UserAsset selectUserXingBi(@Param("xingbi") Integer xingbi,@Param("openId") String openId);

    /**
     * 获取用户资产信息列表
     * @param userId
     * @return
     */
    List<UserAssetInfoModel> getUserAssetInfoList(@Param("userId") String userId,@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);

    /**
     * 获取用户资产总数
     * @param userId
     * @return
     */
    Integer getUserAssetCount(String userId);
}
