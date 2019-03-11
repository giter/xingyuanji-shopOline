package com.shopoline.xingyuanji.service.db1;

import com.shopoline.xingyuanji.entity.UserAddress;
import com.baomidou.mybatisplus.service.IService;
import com.shopoline.xingyuanji.model.UserAddressModel;

import java.util.List;

/**
 * <p>
 * 用户地址相关接口
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
    List<UserAddress> getAddress(String ticketId);

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
    void deleteAddress(String ticketId, String id);

    /**
     *更新地址
     * @param ticketId
     * @param id
     * @param userAddressModel
     */
    void updateAddress(String ticketId, String id, UserAddressModel userAddressModel);

    /**
     * 判断用户是否有默认地址
     * @param ticketId
     * @return
     */
    String isUserAddress(String ticketId);
}
