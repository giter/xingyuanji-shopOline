package com.shopoline.xingyuanji.service.db1.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.shopoline.xingyuanji.Constants;
import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.entity.UserAddress;
import com.shopoline.xingyuanji.entity.UserInfo;
import com.shopoline.xingyuanji.mapper.UserAddressMapper;
import com.shopoline.xingyuanji.model.UserAddressInfoModel;
import com.shopoline.xingyuanji.model.UserAddressModel;
import com.shopoline.xingyuanji.service.db1.IUserAddressService;
import com.shopoline.xingyuanji.service.db1.IUserInfoService;
import com.shopoline.xingyuanji.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>
 * 用户邮寄地址服务实现类
 * </p>
 * @author wuty
 * @since 2019-01-09
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements IUserAddressService {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private IUserInfoService userInfoService;


    /**
     * 写入用户地址
     * @param ticketId
     * @param userAddressModel
     * @return
     */
    @Override
    public synchronized void insertAddress(String ticketId,UserAddressModel userAddressModel) throws Exception {

        UserInfo userInfo = userInfoService.getDB1UserInfo(ticketId);

        boolean tokenResult = RedisUtil.hasKey("INSERTADDRESS"+userInfo.getOpenId());

        if( tokenResult == true ){
            logger.info("<-INSERT_ADDRESS_LOCK->:"+userInfo.getNickName()+"\tBUYER_TOKEN："+userInfo.getOpenId());
            throw new Exception(ExceptionEnum.EXCEPTION_28.getDesc());
        }

        Assert.isTrue(userAddressModel.getPhone().length()==11,ExceptionEnum.EXCEPTION_21.getDesc());
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

        // 判断用户是否存在默认地址
        UserAddress userAddress = this.selectOne(new EntityWrapper<UserAddress>().eq("userId",userInfo.getUserId()).
                eq("def",Constants.DEF_ADDRESS).eq("deleteFlag",Constants.QIYONG).last("Limit 1"));

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

        // 写入token
        RedisUtil.setAddressValueSeconds("INSERTADDRESS"+userInfo.getOpenId(),userInfo.getOpenId());
    }

    /**
     * 获取用户地址
     * @param ticketId
     * @return
     */
    @Override
    public List<UserAddress> getAddress(String ticketId) {

        UserInfo userInfo = userInfoService.getDB1UserInfo(ticketId);
        List<UserAddress> addressList = this.selectList(new EntityWrapper<UserAddress>().eq("userId",userInfo.getUserId()).
                eq("deleteFlag",Constants.QIYONG));
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
    public synchronized void setAddressDef(String ticketId, String id) throws Exception {

        UserInfo userInfo = userInfoService.getDB1UserInfo(ticketId);
        // 判断REDIS中是否有key
        boolean tokenResult = RedisUtil.hasKey("INSERTADDRESS"+userInfo.getOpenId());
        if( tokenResult == true ){
            logger.info("<-SET_ADDRESS_DEF_LOCK->:"+userInfo.getNickName()+"\tBUYER_TOKEN："+userInfo.getOpenId());
            throw new Exception(ExceptionEnum.EXCEPTION_24.getDesc());
        }

        List<UserAddress> userAddressList = this.selectList(new EntityWrapper<UserAddress>().eq("userId",userInfo.getUserId()).
                eq("deleteFlag",Constants.QIYONG));
        // 遍历
        ListIterator<UserAddress> iterator = userAddressList.listIterator();
        while (iterator.hasNext()){
            UserAddress userAddress = iterator.next();
            if(userAddress.getDef() == Constants.DEF_ADDRESS){
                UserAddress address = new UserAddress();
                address.setId(userAddress.getId());
                address.setUserId(userAddress.getUserId());
                address.setName(userAddress.getName());
                address.setPhone(userAddress.getPhone());
                address.setProvince(userAddress.getProvince());
                address.setCity(userAddress.getCity());
                address.setAddress(userAddress.getAddress());
                address.setArea(userAddress.getArea());
                address.setDef(Constants.NO_DEF_ADDRESS);
                address.setEditTime(new Date());
                address.setEditBy("admin");
                address.setDeleteFlag(Constants.QIYONG);
                this.updateById(address);
            }
        }
        UserAddress userAddress = this.selectOne(new EntityWrapper<UserAddress>().eq("id",id).
                eq("userId",userInfo.getUserId()).last("Limit 1"));
        userAddress.setDef(Constants.DEF_ADDRESS);
        this.updateById(userAddress);
        // 服务器慢,写入redis,key过期时间五秒
        RedisUtil.setAddressValueSeconds("SETADDRESSDEF"+userInfo.getOpenId(),userInfo.getOpenId());

    }

    @Override
    public void deleteAddress(String ticketId, String id){

        UserInfo userInfo = userInfoService.getDB1UserInfo(ticketId);
        UserAddress userAddress = this.selectOne(new EntityWrapper<UserAddress>().eq("id",id).
                eq("userId",userInfo.getUserId()).last("Limit 1"));
        // 判断是否为默认地址，如果是则断言
        Assert.isTrue( userAddress.getDef() != Constants.DEF_ADDRESS , ExceptionEnum.EXCEPTION_20.desc);
        userAddress.setDeleteFlag(Constants.WEIQIYONG);
        this.updateById(userAddress);

    }

    /**
     * 更新地址
     * @param ticketId
     * @param id
     * @param userAddressModel
     */
    @Override
    public synchronized void updateAddress(String ticketId, String id, UserAddressModel userAddressModel) throws Exception{

        Assert.isTrue(userAddressModel.getPhone().length()==11,ExceptionEnum.EXCEPTION_21.getDesc());
        UserInfo userInfo = userInfoService.getDB1UserInfo(ticketId);
        UserAddress userAddress = this.selectOne(new EntityWrapper<UserAddress>().eq("id",id).
                eq("userId",userInfo.getUserId()).last("Limit 1"));
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

    /**
     * 判断用户是否有默认地址
     * @param ticketId
     * @return
     */
    @Override
    public String isUserAddress(String ticketId) {

            //获取用户信息
            UserInfo userInfo = userInfoService.getDB1UserInfo(ticketId);
            //获取用户默认地址
            UserAddress userAddress = this.selectOne(new EntityWrapper<UserAddress>().eq("userId",userInfo.getUserId()).
                    eq("def",Constants.DEF_ADDRESS).eq("deleteFlag",Constants.QIYONG).last("Limit 1"));
            // 如果没有默认地址返回0
            if(userAddress == null){
                return "0";
            }
            return "1";
    }

    /**
     * 获取用户地址信息(后台管理)
     * @param userId
     * @return
     */
    @Override
    public List<UserAddressInfoModel> getUserAddressInfoList(String userId) {

        return baseMapper.getUserAddressInfoList(userId);
    }


}
