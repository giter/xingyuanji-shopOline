package com.shopoline.xingyuanji.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.shopoline.xingyuanji.Constants;
import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.entity.UserAddress;
import com.shopoline.xingyuanji.entity.UserInfo;
import com.shopoline.xingyuanji.mapper.UserAddressMapper;
import com.shopoline.xingyuanji.model.UserAddressModel;
import com.shopoline.xingyuanji.service.IUserAddressService;
import com.shopoline.xingyuanji.service.IUserInfoService;
import com.shopoline.xingyuanji.utils.GetOpenId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户邮寄地址 服务实现类
 * </p>
 *
 * @author wuty
 * @since 2019-01-09
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements IUserAddressService {

    @Autowired
    private IUserInfoService userInfoService;



    /**
     * 写入用户地址
     * @param ticketId
     * @param userAddressModel
     * @return
     */
    @Override
    public void insertAddress(String ticketId,UserAddressModel userAddressModel) throws Exception {
        if (userAddressModel.getAddress() == null || userAddressModel.getAddress().equals("")){
            throw new Exception(ExceptionEnum.EXCEPTION_8.getDesc());
        }else if(userAddressModel.getArea() == null || userAddressModel.getArea().equals("")){
            throw new Exception(ExceptionEnum.EXCEPTION_9.getDesc());
        }else if(userAddressModel.getCity() == null || userAddressModel.getCity().equals("")){
            throw new Exception(ExceptionEnum.EXCEPTION_10.getDesc());
        }else if(userAddressModel.getProvince() == null || userAddressModel.getProvince().equals("")){
            throw new Exception(ExceptionEnum.EXCEPTION_11.getDesc());
        }else if(userAddressModel.getName() == null || userAddressModel.getName().equals("")){
            throw new Exception(ExceptionEnum.EXCEPTION_12.getDesc());
        }else if(userAddressModel.getPhone() == null || userAddressModel.getPhone().equals("")){
            throw new Exception(ExceptionEnum.EXCEPTION_13.getDesc());
        }
        String openId = GetOpenId.getOpenId(ticketId);
        UserInfo userInfo = userInfoService.selectOne(new EntityWrapper<UserInfo>().eq("openId",openId).eq("deleteFlag", Constants.QIYONG));


        // 判断用户是否存在默认地址
        UserAddress userAddress = this.selectOne(new EntityWrapper<UserAddress>().eq("userId",userInfo.getUserId()).
                eq("def",Constants.DEF_ADDRESS));
        // 写入地址信息
        UserAddress insertUserAddress = new UserAddress();
        insertUserAddress.setId(IdWorker.get32UUID());
        insertUserAddress.setUserId(userInfo.getUserId());
        insertUserAddress.setName(userAddressModel.getName());
        insertUserAddress.setPhone(userAddressModel.getPhone());
        insertUserAddress.setProvince(userAddressModel.getProvince());
        insertUserAddress.setCity(userAddressModel.getCity());
        insertUserAddress.setAddress(userAddressModel.getAddress());
        if(userAddress == null){
            insertUserAddress.setDef(Constants.DEF_ADDRESS);
        }else{
            insertUserAddress.setDef(Constants.NO_DEF_ADDRESS);
        }
        insertUserAddress.setEditTime(new Date());
        insertUserAddress.setEditBy("admin");
        insertUserAddress.setDeleteFlag(Constants.QIYONG);
        insertUserAddress.setArea(userAddressModel.getArea());
        this.insert(insertUserAddress);

    }

    /**
     * 获取用户地址
     * @param ticketId
     * @return
     */
    @Override
    public List<UserAddress> getAdress(String ticketId) {
        String openId = GetOpenId.getOpenId(ticketId);
        UserInfo userInfo = userInfoService.selectOne(new EntityWrapper<UserInfo>().eq("openId",openId));
        List<UserAddress> addressList = this.selectList(new EntityWrapper<UserAddress>().eq("userId",userInfo.getUserId()).eq("deleteFlag",Constants.QIYONG));
        Assert.isTrue(!addressList.isEmpty(),ExceptionEnum.EXCEPTION_16.getDesc());
        return addressList;
    }

    /**
     * 设置默认地址
     * @param ticketId
     * @param id
     * @return
     */
    @Override
    public void setAddressDef(String ticketId, String id) {

        String openId = GetOpenId.getOpenId(ticketId);
        UserInfo userInfo = userInfoService.selectOne(new EntityWrapper<UserInfo>().eq("openId",openId));
        List<UserAddress> userAddressList = this.selectList(new EntityWrapper<UserAddress>().eq("userId",userInfo.getUserId()));
        for(UserAddress userAddress:userAddressList){
            if(userAddress.getDef() == 1){
                UserAddress address = new UserAddress();
                address.setId(userAddress.getId());
                address.setUserId(userAddress.getUserId());
                address.setName(userAddress.getName());
                address.setPhone(userAddress.getPhone());
                address.setProvince(userAddress.getProvince());
                address.setCity(userAddress.getCity());
                address.setAddress(userAddress.getAddress());
                address.setArea(userAddress.getArea());
                address.setDef(0);
                address.setEditTime(new Date());
                address.setEditBy("admin");
                address.setDeleteFlag(1);
                this.updateById(address);
            }
        }
        UserAddress userAddress = this.selectOne(new EntityWrapper<UserAddress>().eq("id",id).eq("userId",userInfo.getUserId()));
        userAddress.setDef(1);
        this.updateById(userAddress);
    }

    @Override
    public void deleteAddress(String ticketId, String id) {
        String openId = GetOpenId.getOpenId(ticketId);
        UserInfo userInfo = userInfoService.selectOne(new EntityWrapper<UserInfo>().eq("openId",openId));
        UserAddress userAddress = this.selectOne(new EntityWrapper<UserAddress>().eq("id",id).eq("userId",userInfo.getUserId()));
        userAddress.setDeleteFlag(0);
        this.updateById(userAddress);
    }

    /**
     * 更新地址
     * @param ticketId
     * @param id
     * @param userAddressModel
     */
    @Override
    public void updateAddress(String ticketId, String id, UserAddressModel userAddressModel) {
        String openId = GetOpenId.getOpenId(ticketId);
        UserInfo userInfo = userInfoService.selectOne(new EntityWrapper<UserInfo>().eq("openId",openId));
        UserAddress userAddress = this.selectOne(new EntityWrapper<UserAddress>().eq("id",id).eq("userId",userInfo.getUserId()));
        userAddress.setId(id);
        userAddress.setUserId(userInfo.getUserId());
        userAddress.setName(userAddressModel.getName());
        userAddress.setPhone(userAddressModel.getPhone());
        userAddress.setProvince(userAddressModel.getProvince());
        userAddress.setCity(userAddressModel.getCity());
        userAddress.setAddress(userAddressModel.getAddress());
        userAddress.setArea(userAddressModel.getArea());
        userAddress.setEditTime(new Date());
        this.updateById(userAddress);
    }
}
