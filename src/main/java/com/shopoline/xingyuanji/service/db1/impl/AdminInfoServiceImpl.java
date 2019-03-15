package com.shopoline.xingyuanji.service.db1.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.shopoline.xingyuanji.Constants;
import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.entity.AdminInfo;
import com.shopoline.xingyuanji.entity.UserAsset;
import com.shopoline.xingyuanji.entity.UserInfo;
import com.shopoline.xingyuanji.mapper.AdminInfoMapper;
import com.shopoline.xingyuanji.model.*;
import com.shopoline.xingyuanji.service.db1.IAdminInfoService;
import com.shopoline.xingyuanji.service.db1.IUserAddressService;
import com.shopoline.xingyuanji.service.db1.IUserAssetService;
import com.shopoline.xingyuanji.service.db1.IUserInfoService;
import com.shopoline.xingyuanji.vo.AdminInfoVO;
import com.shopoline.xingyuanji.vo.UserAssetListVO;
import com.shopoline.xingyuanji.vo.UserInfoListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuty
 * @since 2019-03-13
 */
@Service
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements IAdminInfoService {

    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IUserAssetService userAssetService;
    @Autowired
    private IUserAddressService userAddressService;


    /**
     * 后台管理登陆
     * @param adminLoginModel
     * @return
     */
    @Override
    public String adminLogin(AdminLoginModel adminLoginModel){

        // 获取用户信息
        AdminInfo adminInfo = this.selectOne(new EntityWrapper<AdminInfo>().eq("userName",adminLoginModel.getUserName()).
                eq("passWord",adminLoginModel.getPassWord()).eq("deleteFlag", Constants.QIYONG).last("Limit 1"));
        Assert.isTrue(adminInfo != null,ExceptionEnum.EXCEPTION_23.getDesc());
        // 运用Model内重写的equals方法和hashCode方法比较用户信息是否一致
        AdminLoginModel adminLogin = new AdminLoginModel(adminInfo.getUserName(),adminInfo.getPassWord());
        Assert.isTrue(adminLogin.equals(adminLoginModel),ExceptionEnum.EXCEPTION_23.getDesc());

        return "loginSuccess";
    }

    /**
     * 增加或修改管理员信息
     * @param privilegeManageModel
     * @return
     */
    @Override
    public void privilegeManage(PrivilegeManageModel privilegeManageModel) {

        // 查询数据库中管理员记录
        AdminInfo adminInfo = this.selectOne(new EntityWrapper<AdminInfo>().eq("id",privilegeManageModel.getId()).
                eq("deleteFlag",Constants.QIYONG));
        // 没有记录插入数据,有就更新
        if(adminInfo == null){
            AdminInfo insertAdmin = new AdminInfo();
            insertAdmin.setId(IdWorker.get32UUID());
            insertAdmin.setName(privilegeManageModel.getName());
            insertAdmin.setUserName(privilegeManageModel.getUserName());
            insertAdmin.setPassWord(privilegeManageModel.getPassWord());
            insertAdmin.setPosition(privilegeManageModel.getPosition());
            insertAdmin.setEditBy(privilegeManageModel.getEditBy());
            insertAdmin.setEditTime(new Date());
            insertAdmin.setDeleteFlag(privilegeManageModel.getDeleteFlag());
            this.insert(insertAdmin);
        }else{
            adminInfo.setId(privilegeManageModel.getId());
            adminInfo.setName(privilegeManageModel.getName());
            adminInfo.setUserName(privilegeManageModel.getUserName());
            adminInfo.setPassWord(privilegeManageModel.getPassWord());
            adminInfo.setPosition(privilegeManageModel.getPosition());
            adminInfo.setEditBy(privilegeManageModel.getEditBy());
            adminInfo.setEditTime(new Date());
            adminInfo.setDeleteFlag(privilegeManageModel.getDeleteFlag());
            this.updateById(adminInfo);
        }
    }

    /**
     * 获取管理员信息
     * @return
     */
    @Override
    public List<AdminInfoVO> getAdminInfoList() {

        List<AdminInfo> adminInfoList = this.selectList(new EntityWrapper<AdminInfo>().orderBy("editTime",false));
        // 选用LinkedList因为插入删除快
        List<AdminInfoVO> adminInfoVOList = new LinkedList<>();
        // 遍历格式化时间
        for(ListIterator<AdminInfo> adminInfoListIterator = adminInfoList.listIterator();adminInfoListIterator.hasNext();){
                AdminInfoVO adminInfoVO = new AdminInfoVO();
                AdminInfo adminInfo = adminInfoListIterator.next();
                adminInfoVO.setId(adminInfo.getId());
                adminInfoVO.setUserName(adminInfo.getUserName());
                adminInfoVO.setName(adminInfo.getName());
                adminInfoVO.setPassWord(adminInfo.getPassWord());
                adminInfoVO.setPosition(adminInfo.getPosition());
                adminInfoVO.setEditBy(adminInfo.getEditBy());
                adminInfoVO.setEditTime(DateFormat.getDateInstance(DateFormat.FULL).format(adminInfo.getEditTime()));
                adminInfoVO.setDeleteFlag(adminInfo.getDeleteFlag());
                adminInfoVOList.add(adminInfoVO);
        }
        return adminInfoVOList;
    }

    /**
     * 获取用户信息列表(包含条件查询)
     * @param nickName
     * @param openId
     * @return
     */
    @Override
    public UserInfoListVO getUserInfoList(String nickName, String openId,String pageNum) {

        // 用户信息
        List<UserInfo> userInfoList;
        if(pageNum != null && !pageNum.equals("")){
            // 每页记录数量
            Integer pageSize = 6;
            // 根据页码计算查询条数
            Integer pageStart = (Integer.valueOf(pageNum) - 1) * pageSize;
           userInfoList = userInfoService.selectUserInfoByCondition(nickName,openId,pageStart,pageSize);
        }else{
            userInfoList = userInfoService.selectUserInfoByInformation(nickName,openId);
        }
        List<UserInfoListModel> userInfoListVOList = new LinkedList<>();
        // 遍历
        for (ListIterator<UserInfo> userInfoIterator = userInfoList.listIterator(); userInfoIterator.hasNext(); ) {
             UserInfo userInfo = userInfoIterator.next();
             UserInfoListModel userInfoListModel = new UserInfoListModel();
                userInfoListModel.setUserId(userInfo.getUserId());
                userInfoListModel.setOpenId(userInfo.getOpenId());
                userInfoListModel.setNickName(userInfo.getNickName());
                userInfoListModel.setSex(String.valueOf(userInfo.getSex()));
                userInfoListModel.setEditTime(DateFormat.getDateInstance(DateFormat.FULL).format(userInfo.getEditTime()));

                userInfoListVOList.add(userInfoListModel);
        }
        // VO
        UserInfoListVO userInfoListVO = new UserInfoListVO();
            userInfoListVO.setUserInfoListModelList(userInfoListVOList);
            userInfoListVO.setCount(userInfoService.selectCount(new EntityWrapper<>()));
        return userInfoListVO;
    }

    /**
     * 查询用户资产信息列表
     * @param userId
     * @return
     */
    @Override
    public UserAssetListVO getUserAssetList(String userId,String pageNum) {
        // 每页记录数量
        Integer pageSize = 6;
        // 根据页码计算查询条数
        Integer pageStart = (Integer.valueOf(pageNum) - 1) * pageSize;
        // 获取用户资产信息列表
        List<UserAssetInfoModel> userAssetList = userAssetService.getUserAssetInfoList(userId,pageStart,pageSize);
        List<UserAssetListModel> userAssetModelList = new LinkedList<>();
        //遍历
        for(ListIterator<UserAssetInfoModel> iterator = userAssetList.listIterator();iterator.hasNext();){
            UserAssetListModel userAssetListModel = new UserAssetListModel();
            UserAssetInfoModel userAssetInfoModel = iterator.next();
            userAssetListModel.setAmount(userAssetInfoModel.getAmount());
            userAssetListModel.setAmountId(userAssetInfoModel.getAmountId());
            userAssetListModel.setAmountType(userAssetInfoModel.getAmountType());
            userAssetListModel.setDeletFlag(userAssetInfoModel.getDeletFlag());
            userAssetListModel.setEditTime(DateFormat.getDateInstance(DateFormat.FULL).format(userAssetInfoModel.getEditTime()));
            userAssetListModel.setNickName(userAssetInfoModel.getNickName());
            userAssetListModel.setOpenId(userAssetInfoModel.getOpenId());
            userAssetListModel.setUserId(userAssetInfoModel.getUserId());
            userAssetModelList.add(userAssetListModel);
        }
        // VO
        UserAssetListVO userAssetListVO = new UserAssetListVO();
        userAssetListVO.setUserAssetListModelList(userAssetModelList);
        userAssetListVO.setCount(userAssetService.getUserAssetCount(userId));
        userAssetListVO.setPageCount(userAssetService.selectCount(new EntityWrapper<UserAsset>().eq("userId",userId)));

        return userAssetListVO;
    }

    /**
     * 更新用户资产状态
     * @param amountId
     * @return
     */
    @Override
    public void replaceUserAsset(String amountId,String deleteFlag) {

        Integer status = null;
        if(deleteFlag.equals("1")){
            status = 0;
        }else if(deleteFlag.equals("0")){
            status = 1;
        }
        UserAsset userAsset = new UserAsset();
        userAsset.setId(amountId);
        userAsset.setDeltFlag(status);
        userAssetService.updateById(userAsset);
    }


    /**
     * 获取用户地址信息
     * @param userId
     * @return
     */
    @Override
    public List<UserAddressInfoModel> getUserAddressInfoList(String userId) {

        List<UserAddressInfoModel> userAddressInfoModel = userAddressService.getUserAddressInfoList(userId);
        return userAddressInfoModel;
    }


}
