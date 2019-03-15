package com.shopoline.xingyuanji.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.shopoline.xingyuanji.entity.AdminInfo;
import com.shopoline.xingyuanji.model.AdminLoginModel;
import com.shopoline.xingyuanji.model.PrivilegeManageModel;
import com.shopoline.xingyuanji.model.UserAddressInfoModel;
import com.shopoline.xingyuanji.vo.AdminInfoVO;
import com.shopoline.xingyuanji.vo.UserAssetListVO;
import com.shopoline.xingyuanji.vo.UserInfoListVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuty
 * @since 2019-03-13
 */
public interface IAdminInfoService extends IService<AdminInfo> {

    /**
     * 后台管理登陆
     * @param adminLoginModel
     * @return
     */
    String adminLogin(AdminLoginModel adminLoginModel);

    /**
     * 增加或修改管理员信息
     * @param privilegeManageModel
     * @return
     */
    void privilegeManage(PrivilegeManageModel privilegeManageModel);

    /**
     * 获取管理员信息
     * @return
     */
    List<AdminInfoVO> getAdminInfoList();

    /**
     * 获取用户信息列表(包含条件查询)
     * @param nickName
     * @param openId
     * @return
     */
    UserInfoListVO getUserInfoList(String nickName, String openId,String pageNum);

    /**
     * 查询用户资产列表
     * @param userId
     * @return
     */
    UserAssetListVO getUserAssetList(String userId,String pageNum);

    /**
     * 更新用户资产状态
     * @param amountId
     * @return
     */
    void replaceUserAsset(String amountId,String deleteFlag);


    /**
     * 获取用户地址信息
     * @param userId
     * @return
     */
    List<UserAddressInfoModel> getUserAddressInfoList(String userId);

}
