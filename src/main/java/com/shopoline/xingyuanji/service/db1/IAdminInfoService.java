package com.shopoline.xingyuanji.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.shopoline.xingyuanji.entity.AdminInfo;
import com.shopoline.xingyuanji.model.*;
import com.shopoline.xingyuanji.vo.*;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 删除用户地址
     */
    void deleteUserAddress(String addressId) throws Exception;

    /**
     * 变更用户默认地址
     * @param addressId
     * @param userId
     */
    void changeUserDefAddress(String addressId, String userId) throws Exception;

    /**
     * 变更或增加用户地址信息
     * @param changeUserAddressInfoModel
     * @return
     */
    void changeUserAddressInfo(ChangeUserAddressInfoModel changeUserAddressInfoModel);

    /**
     *  获取用户购买记录
     * @param openId
     * @return
     */
    UserShopLogInfoVO getUserShopLogInfo(String openId, String pageNum);

    /**
     * 删除用户购买记录
     * @param shopLogId
     * @return
     */
    void deleteShopLog(String shopLogId,String deleteFlag);

    /**
     * 输入用户运单号+变更物流状态
     * @param shopLogId
     */
    void inputUserWaybill(String shopLogId,String ZIPNum);

    /**
     * 获取全部用户购买记录
     * @param pageNum
     * @return
     */
    AllShopLogVO getAllShopLog(String pageNum, String days, String nickName, String openId);

    /**
     * 获取商品列表
     * @param productType
     * @return
     */
    AdminGetProductVO adminGetProductList(String pageNum,String productType);

    /**
     * 上传图片
     * @param file
     * @param style
     * @return
     */
    ImgUploadVM uploadImg(MultipartFile file, String style,String productId) throws Exception;

    /**
     * 插入或更新产品信息
     * @param productInfoModel
     */
    PutProductInfoVO putProductInfo(ProductInfoModel productInfoModel);

    /**
     * 添加商品
     * @param productInfoModel
     * @param file
     */
    void insertProductInfo(MultipartFile file,ProductInfoModel productInfoModel) throws Exception;

    /**
     * 获取数据详情
     */
    DataVO getInfoData();

    /**
     * 更改产品状态
     * @param productId
     * @param status
     */
    void changeProductStatus(String productId, String status);
}
