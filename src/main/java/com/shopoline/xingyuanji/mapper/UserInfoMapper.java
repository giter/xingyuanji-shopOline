package com.shopoline.xingyuanji.mapper;

import com.shopoline.xingyuanji.entity.UserInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息
 Mapper 接口
 * </p>
 *
 * @author wuty
 * @since 2019-01-09
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 条件查询用户信息
     * @param nickName
     * @param openId
     * @return
     */
    List<UserInfo> selectUserInfoByCondition(@Param("nickName") String nickName, @Param("openId") String openId,
                                             @Param("pageStart")Integer pageStart,@Param("pageSize")Integer pageSize);

    /**
     * 按条件搜索
     * @param nickName
     * @param openId
     * @return
     */
    List<UserInfo> selectUserInfoByInformation(@Param("nickName") String nickName,@Param("openId") String openId);

    List<UserInfo> getUserInfoByLike(@Param("nickName") String nickName);

}
