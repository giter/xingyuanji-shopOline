package com.shopoline.xingyuanji.service;

import com.shopoline.xingyuanji.entity.UserAddress;
import com.baomidou.mybatisplus.service.IService;
import com.shopoline.xingyuanji.model.UserAddressModel;

import java.util.List;

/**
 * <p>
 * 用户邮寄地址 服务类
 * </p>
 *
 * @author wuty
 * @since 2019-01-09
 */
public interface IUserAddressService extends IService<UserAddress> {

    /**
     * 写入用户地址
     * @param ticketId
     * @param userAddressModel
     * @return
     */
    void insertAddress(String ticketId, UserAddressModel userAddressModel) throws Exception;

    /**
     * 获取用户地址
     * @param ticketId
     * @return
     */
    List<UserAddress> getAdress(String ticketId);

    /**
     * 设置默认地址
     * @param ticketId
     * @param id
     * @return
     */
    void setAddressDef(String ticketId, String id);

    /**
     * 删除地址
     * @param ticketId
     * @param id
     */
    void deleteAddress(String ticketId, String id) throws Exception;

    /**
     *更新地址
     * @param ticketId
     * @param id
     * @param userAddressModel
     */
    void updateAddress(String ticketId, String id, UserAddressModel userAddressModel);
}
