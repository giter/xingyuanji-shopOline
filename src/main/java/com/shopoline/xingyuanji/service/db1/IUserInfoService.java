package com.shopoline.xingyuanji.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.shopoline.xingyuanji.entity.UserInfo;
import com.shopoline.xingyuanji.model.SignModel;
import com.shopoline.xingyuanji.vo.UserInfoVO;

import java.util.List;

/**
 * <p>
 * 用户信息相关接口
 * </p>
 *
 * @author wuty
 * @since 2019-01-09
 */
public interface IUserInfoService extends IService<UserInfo> {

    //微信登陆
    Object WXLogin(String code) throws Exception;

    /**
     * 获取个人信息
     * @param ticketId
     * @return
     */
    UserInfoVO getUserInfo(String ticketId) throws Exception;


    /**
     * 获取Sign
     * @return
     */
    SignModel getSign();

    /**
     * 获取DB1用户信息
     * @param ticketId
     * @return
     */
    UserInfo getDB1UserInfo(String ticketId);

    /**
     * 条件查询用户信息
     * @param nickName
     * @param openId
     * @return
     */
    List<UserInfo> selectUserInfoByCondition(String nickName, String openId,Integer pageStart,Integer pageSize);

    /**
     * 按条件搜索
     * @param nickName
     * @param openId
     * @return
     */
    List<UserInfo> selectUserInfoByInformation(String nickName, String openId);

    UserInfo getRedPacketUserInfo(String openId);
}
