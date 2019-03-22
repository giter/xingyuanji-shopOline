package com.shopoline.xingyuanji.service.db1.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.shopoline.xingyuanji.Constants;
import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.entity.*;
import com.shopoline.xingyuanji.mapper.AdminInfoMapper;
import com.shopoline.xingyuanji.model.*;
import com.shopoline.xingyuanji.service.db1.*;
import com.shopoline.xingyuanji.utils.UploadUtil;
import com.shopoline.xingyuanji.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.util.*;

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
    @Autowired
    private IShopLogService shopLogService;
    @Autowired
    private IProductInfoService productInfoService;

    @Value("${boxProductUploadDir}")
    private String boxProductUploadDir;

    @Value("${socerProductUploadDir}")
    private String socerProductUploadDir;

    @Value("${shopPicUploadDir}")
    private String shopPicUploadDir;

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

    /**
     * 删除用户地址
     */
    @Override
    public void deleteUserAddress(String addressId) throws Exception {

        UserAddress userAddress = userAddressService.selectOne(new EntityWrapper<UserAddress>().eq("id",addressId).last("Limit 1"));
        if(userAddress.getDef() == Constants.DEF_ADDRESS && userAddress.getDeleteFlag() == Constants.QIYONG){
            throw new Exception(ExceptionEnum.EXCEPTION_20.getDesc());
        }
        userAddressService.delete(new EntityWrapper<UserAddress>().eq("id",addressId));
    }

    /**
     * 变更用户默认地址
     * @param addressId
     * @param userId
     */
    @Override
    public void changeUserDefAddress(String addressId, String userId) throws Exception{

        UserAddress userAddress = userAddressService.selectOne(new EntityWrapper<UserAddress>().eq("userId",userId).
                eq("id",addressId).last("Limit 1"));
        if(userAddress.getDef() == Constants.DEF_ADDRESS && userAddress.getDeleteFlag() == Constants.QIYONG){
            throw new Exception(ExceptionEnum.EXCEPTION_25.getDesc());
        }
        // 将所有记录变为非默认
        List<UserAddress> userAddressList = userAddressService.selectList(new EntityWrapper<UserAddress>().eq("userId",userId));
        for(ListIterator<UserAddress> iterator = userAddressList.listIterator();iterator.hasNext();){
            UserAddress updateAddress = iterator.next();
            updateAddress.setDef(Constants.NO_DEF_ADDRESS);
            userAddressService.updateById(updateAddress);
        }
        UserAddress changeAddressDef = new UserAddress();
        changeAddressDef.setId(addressId);
        changeAddressDef.setDef(Constants.DEF_ADDRESS);
        changeAddressDef.setDeleteFlag(Constants.QIYONG);
        userAddressService.updateById(changeAddressDef);

    }

    /**
     * 变更或增加用户地址信息
     * @param changeUserAddressInfoModel
     * @return
     */
    @Override
    public void changeUserAddressInfo(ChangeUserAddressInfoModel changeUserAddressInfoModel) {

        // 新增
        if(changeUserAddressInfoModel.getAddressId().equals("") || changeUserAddressInfoModel.getAddressId().equals("")){
            // 获取用户地址信息
            UserAddress userDef = userAddressService.selectOne(new EntityWrapper<UserAddress>().eq("userId",changeUserAddressInfoModel.getUserId()).
                    eq("def",Constants.DEF_ADDRESS).eq("deleteFlag",Constants.QIYONG).last("Limit 1"));

            UserAddress userAddress = new UserAddress();
            userAddress.setId(IdWorker.get32UUID());
            userAddress.setUserId(changeUserAddressInfoModel.getUserId());
            userAddress.setName(changeUserAddressInfoModel.getName());
            userAddress.setPhone(changeUserAddressInfoModel.getPhone());
            userAddress.setProvince(changeUserAddressInfoModel.getProvince());
            userAddress.setCity(changeUserAddressInfoModel.getCity());
            userAddress.setArea(changeUserAddressInfoModel.getArea());
            userAddress.setAddress(changeUserAddressInfoModel.getAddress());
            if(userDef == null){
                userAddress.setDef(Constants.DEF_ADDRESS);
            }else {
                userAddress.setDef(Constants.NO_DEF_ADDRESS);
            }
            userAddress.setEditTime(new Date());
            userAddress.setEditBy(Constants.ADMIN);
            userAddress.setDeleteFlag(Constants.QIYONG);
            userAddressService.insert(userAddress);
        }else{
            // 更新地址
            // 获取用户地址信息
            UserAddress userAddress = userAddressService.selectOne(new EntityWrapper<UserAddress>().
                    eq("userId",changeUserAddressInfoModel.getUserId()).
                    eq("id",changeUserAddressInfoModel.getAddressId()).last("Limit 1"));

            if(changeUserAddressInfoModel.getName() != null || !changeUserAddressInfoModel.getName().equals("")){
                userAddress.setName(changeUserAddressInfoModel.getName());
            }
            if(changeUserAddressInfoModel.getPhone() != null || !changeUserAddressInfoModel.getPhone().equals("")){
                userAddress.setPhone(changeUserAddressInfoModel.getPhone());
            }
            if(changeUserAddressInfoModel.getProvince() != null || !changeUserAddressInfoModel.getProvince().equals("")){
                userAddress.setProvince(changeUserAddressInfoModel.getProvince());
            }
            if(changeUserAddressInfoModel.getCity() != null || !changeUserAddressInfoModel.getCity().equals("")){
                userAddress.setCity(changeUserAddressInfoModel.getCity());
            }
            if(changeUserAddressInfoModel.getArea() != null || !changeUserAddressInfoModel.getArea().equals("")){
                userAddress.setArea(changeUserAddressInfoModel.getArea());
            }
            if(changeUserAddressInfoModel.getAddress() != null || !changeUserAddressInfoModel.getAddress().equals("")){
                userAddress.setAddress(changeUserAddressInfoModel.getAddress());
            }
            if(changeUserAddressInfoModel.getDeleteFlag() != null || !changeUserAddressInfoModel.getDeleteFlag().equals("")){
                if(userAddress.getDef() == 1 && userAddress.getDeleteFlag() == 0 ){
                    userAddress.setDeleteFlag(Constants.WEIQIYONG);
                }else if(userAddress.getDef() == 1 && userAddress.getDeleteFlag() == 1 ){
                    userAddress.setDeleteFlag(Constants.QIYONG);
                }else{
                    userAddress.setDeleteFlag(Integer.valueOf(changeUserAddressInfoModel.getDeleteFlag()));
                }
            }
            userAddressService.updateById(userAddress);
        }
    }

    /**
     *  获取用户购买记录
     * @param openId
     * @return
     */
    @Override
    public UserShopLogInfoVO getUserShopLogInfo(String openId, String pageNum) {

        // 每页记录数量
        Integer pageSize = 6;
        // 根据页码计算查询条数
        Integer pageStart = (Integer.valueOf(pageNum) - 1) * pageSize;

        List<UserShopLogInfoModel> userShopLogInfoModelList = baseMapper.getUserShopLogInfo(openId,pageStart,pageSize);

        for(ListIterator<UserShopLogInfoModel> iterator = userShopLogInfoModelList.listIterator();iterator.hasNext();){
            UserShopLogInfoModel userShopLogInfoModel = iterator.next();
            if(userShopLogInfoModel.getAddressId() == null || userShopLogInfoModel.getAddressId().equals("")){
                UserAddress userAddress = userAddressService.selectOne(new EntityWrapper<UserAddress>().eq("userId",userShopLogInfoModel.getUserId()).
                        eq("def",Constants.DEF_ADDRESS).eq("deleteFlag",Constants.QIYONG).last("Limit 1"));

                userShopLogInfoModel.setName(userAddress.getName());
                userShopLogInfoModel.setPhone(userAddress.getPhone());
                userShopLogInfoModel.setProvince(userAddress.getProvince());
                userShopLogInfoModel.setCity(userAddress.getCity());
                userShopLogInfoModel.setArea(userAddress.getArea());
                userShopLogInfoModel.setAddress(userAddress.getAddress());
            }
        }

        UserShopLogInfoVO userShopLogInfoVO = new UserShopLogInfoVO();
        userShopLogInfoVO.setUserShopLogInfolList(userShopLogInfoModelList);
        userShopLogInfoVO.setPageCount(shopLogService.selectCount(new EntityWrapper<ShopLog>().eq("openId",openId)));
        return userShopLogInfoVO;
    }

    /**
     * 删除用户购买记录
     * @param shopLogId
     * @return
     */
    @Override
    public void deleteShopLog(String shopLogId,String deleteFlag) {
        ShopLog shopLog = shopLogService.selectOne(new EntityWrapper<ShopLog>().eq("id",shopLogId).last("Limit 1"));
        if(deleteFlag.equals("1")){
            shopLog.setDeleteFlag(0);
        }else{
            shopLog.setDeleteFlag(1);
        }
        shopLogService.updateById(shopLog);
    }

    /**
     *  输入用户运单号+变更物流状态
     * @param shopLogId
     */
    @Override
    public void inputUserWaybill(String shopLogId,String ZIPNum) {
        ShopLog shopLog = shopLogService.selectOne(new EntityWrapper<ShopLog>().eq("id",shopLogId).last("Limit 1"));
        shopLog.setZIPNum(ZIPNum);
        shopLog.setIsDeliver(Constants.YI_FA_HUO);
        shopLogService.updateById(shopLog);
    }

    /**
     * 获取全部用户购买记录
     * @param pageNum
     * @return
     */
    @Override
    public AllShopLogVO getAllShopLog(String pageNum,String days, String nickName, String openId) {

        AllShopLogVO allShopLogVO = new AllShopLogVO();
        // 每页记录数量
        Integer pageSize = 6;
        // 根据页码计算查询条数
        Integer pageStart = (Integer.valueOf(pageNum) - 1) * pageSize;
        Integer dayNum;
        if(days == null){
            dayNum = 0;
        }else {
            dayNum = Integer.valueOf(days) - 1;
        }
        List<UserShopLogInfoModel> logInfoModelList = new ArrayList<>();
        if(days == null && nickName.equals("0") && openId.equals("0")){
            // 所有记录
            logInfoModelList = baseMapper.getAllShopLog(pageStart,pageSize);
            allShopLogVO.setPageCount(String.valueOf(shopLogService.selectCount(new EntityWrapper<>())));
        }else if(!nickName.equals("0")){
            // 模糊查询NickName
            // 获取模糊查询用户信息
            List<UserInfo> userInfo = userInfoService.getUserInfoByLike(nickName);
            // 遍历userInfo
            for(ListIterator<UserInfo> userInfoIterator = userInfo.listIterator();userInfoIterator.hasNext();){
                UserInfo userInfo1 = userInfoIterator.next();
                List<ShopLog> shopLogList = shopLogService.selectList(new EntityWrapper<ShopLog>().eq("userId",userInfo1.getUserId()));
                for(ListIterator<ShopLog> shopLogListIterator = shopLogList.listIterator();shopLogListIterator.hasNext();){
                    ShopLog shopLog = shopLogListIterator.next();
                    UserShopLogInfoModel userShopLogInfoModel = new UserShopLogInfoModel();
                    userShopLogInfoModel.setOpenId(shopLog.getOpenId());
                    userShopLogInfoModel.setAddressId(shopLog.getAddressId());
                    userShopLogInfoModel.setShopLogId(shopLog.getId());
                    userShopLogInfoModel.setDeleteFlag(String.valueOf(shopLog.getDeleteFlag()));
                    userShopLogInfoModel.setIsDeliver(shopLog.getIsDeliver());
                    userShopLogInfoModel.setZIPAmount(shopLog.getZIPAmount());
                    userShopLogInfoModel.setZIPNum(shopLog.getZIPNum());
                    userShopLogInfoModel.setExpress(String.valueOf(shopLog.getExpress()));
                    userShopLogInfoModel.setOutTradeNo(shopLog.getOutTradeNo());
                    userShopLogInfoModel.setZIPOutTradeNo(shopLog.getZIPOutTradeNo());
                    userShopLogInfoModel.setIsPay(shopLog.getIsPay());
                    userShopLogInfoModel.setTotalFee(shopLog.getTotalFee());
                    userShopLogInfoModel.setUpdateTime(String.valueOf(shopLog.getUpdateTime()));
                    userShopLogInfoModel.setBoxId(shopLog.getBoxId());
                    userShopLogInfoModel.setEditTime(String.valueOf(shopLog.getEditTime()));
                    userShopLogInfoModel.setGoodsId(String.valueOf(shopLog.getGoodsId()));
                    logInfoModelList.add(userShopLogInfoModel);
                }
            }
            allShopLogVO.setPageCount(String.valueOf(logInfoModelList.size()));
        }else if(!openId.equals("0")){
            logInfoModelList = baseMapper.getAllShopLogByOpenId(pageStart,pageSize,openId);
            allShopLogVO.setPageCount(String.valueOf(shopLogService.selectCount(new EntityWrapper<ShopLog>().eq("openId",openId))));
        }else if(!days.equals("0")){
            logInfoModelList = baseMapper.getDaysShopLogByOpenId(pageStart,pageSize,dayNum,openId);
            allShopLogVO.setPageCount(baseMapper.getDaysCount(dayNum));
        }

        for(ListIterator<UserShopLogInfoModel> iterator = logInfoModelList.listIterator();iterator.hasNext();){
            UserShopLogInfoModel userShopLogInfo = iterator.next();
            // 获取用户地址
            UserAddress userAddress = userAddressService.selectOne(new EntityWrapper<UserAddress>().eq("id",userShopLogInfo.getAddressId()).last("Limit 1"));
            // 如果没有用户地址Id获取用户默认启用地址
            if(userAddress == null){
                UserAddress userAddressDef = userAddressService.selectOne(new EntityWrapper<UserAddress>().eq("def",Constants.DEF_ADDRESS).
                        eq("deleteFlag",Constants.QIYONG).last("Limit 1"));
                userShopLogInfo.setName(userAddressDef.getName());
                userShopLogInfo.setPhone(userAddressDef.getPhone());
                userShopLogInfo.setProvince(userAddressDef.getProvince());
                userShopLogInfo.setCity(userAddressDef.getCity());
                userShopLogInfo.setArea(userAddressDef.getArea());
                userShopLogInfo.setAddress(userAddressDef.getAddress());
            }else{
                userShopLogInfo.setName(userAddress.getName());
                userShopLogInfo.setPhone(userAddress.getPhone());
                userShopLogInfo.setProvince(userAddress.getProvince());
                userShopLogInfo.setCity(userAddress.getCity());
                userShopLogInfo.setArea(userAddress.getArea());
                userShopLogInfo.setAddress(userAddress.getAddress());
            }
            // 获取用户信息
            UserInfo userInfo = userInfoService.selectOne(new EntityWrapper<UserInfo>().eq("openId",userShopLogInfo.getOpenId()).last("Limit 1"));
            userShopLogInfo.setUserId(userInfo.getUserId());
            userShopLogInfo.setName(userInfo.getNickName());
            userShopLogInfo.setNickName(userInfo.getNickName());
            // 获取商品信息
            ProductInfo productInfo = productInfoService.selectOne(new EntityWrapper<ProductInfo>().eq("id",userShopLogInfo.getGoodsId()).last("Limit 1"));
            userShopLogInfo.setGoodsname(productInfo.getGoodsname());
        }
        // VO
        allShopLogVO.setAllShopLogList(logInfoModelList);
        return allShopLogVO;
    }

    @Override
    public AdminGetProductVO adminGetProductList(String pageNum,String productType) {

        // 每页记录数量
        Integer pageSize = 6;
        // 根据页码计算查询条数
        Integer pageStart = (Integer.valueOf(pageNum) - 1) * pageSize;
        List<ProductListModel> productListModelList = new LinkedList<>();

        if(!productType.equals("3")){
            List<ProductInfo> productInfoList = baseMapper.getProductInfo(productType);
            for(ListIterator<ProductInfo> iterator = productInfoList.listIterator();iterator.hasNext();){
                ProductInfo productInfo1 = iterator.next();
                ProductListModel productListModel = new ProductListModel();
                productListModel.setProductId(String.valueOf(productInfo1.getId()));
                productListModel.setProductName(productInfo1.getGoodsname());
                productListModel.setPrice(String.valueOf(productInfo1.getPrice()));
                if(productInfo1.getStyle().equals("1")){
                    productListModel.setProductImg("/productPic/"+productInfo1.getImg());
                }else{
                    productListModel.setProductImg(productInfo1.getImg());
                }
                if(productInfo1.getStyle().equals("1")){
                    productListModel.setProductShopImg("/shop/"+productInfo1.getShopImg());
                }else{
                    productListModel.setProductShopImg(productInfo1.getShopImg());
                }
                productListModel.setProductNum(String.valueOf(productInfo1.getProductCount()));
                productListModel.setProductStyle(productInfo1.getStyle());
                productListModel.setProductSocer(productInfo1.getSocer());
                productListModel.setEditTime(String.valueOf(productInfo1.getEditTime()));
                productListModel.setProductStatus(productInfo1.getDeleteFlag());
                productListModelList.add(productListModel);
            }
        }else{
            List<ProductInfo> productInfoList = baseMapper.getShopInfoList(pageStart,pageSize);

            for(ListIterator<ProductInfo> iterator = productInfoList.listIterator();iterator.hasNext();){
                ProductInfo productInfo = iterator.next();
                ProductListModel productListModel1 = new ProductListModel();
                productListModel1.setProductId(String.valueOf(productInfo.getId()));
                productListModel1.setProductName(productInfo.getGoodsname());
                productListModel1.setPrice(String.valueOf(productInfo.getPrice()));
                if(productInfo.getStyle().equals("1")){
                    productListModel1.setProductImg("/productPic/"+productInfo.getImg());
                }else{
                    productListModel1.setProductImg(productInfo.getImg());
                }
                if(productInfo.getStyle().equals("1")){
                    productListModel1.setProductShopImg("/shop/"+productInfo.getShopImg());
                }else{
                    productListModel1.setProductShopImg(productInfo.getShopImg());
                }
                productListModel1.setProductNum(String.valueOf(productInfo.getProductCount()));
                productListModel1.setProductStyle(productInfo.getStyle());
                productListModel1.setProductSocer(productInfo.getSocer());
                productListModel1.setEditTime(String.valueOf(productInfo.getEditTime()));
                productListModel1.setProductStatus(productInfo.getDeleteFlag());
                productListModelList.add(productListModel1);
            }
        }


        AdminGetProductVO adminGetProductVO = new AdminGetProductVO();
        adminGetProductVO.setProductList(productListModelList);
        adminGetProductVO.setPageCount(String.valueOf(productInfoService.selectCount(new EntityWrapper<>())));
        return adminGetProductVO;
    }

    /**
     * 上传图片
     * @param file
     * @param style
     * @return
     */
    @Override
    public ImgUploadVM uploadImg(MultipartFile file, String style,String productId) throws Exception {

        String path = null;
        if(style.equals("0")){
            path = boxProductUploadDir;
        }else if(style.equals("1")){
            path = socerProductUploadDir;
        }else if(style.equals("2")){
            path = shopPicUploadDir;
        }
        ImgUploadVM imgUploadVM = UploadUtil.upload(file,path);

        // 获取商品信息
        ProductInfo productInfo = productInfoService.selectOne(new EntityWrapper<ProductInfo>().eq("id",productId).last("Limit 1"));
        productInfo.setImg(imgUploadVM.getImgFullName());
        if(style.equals("2")){
            productInfo.setShopImg(imgUploadVM.getImgFullName());
        }
        productInfoService.updateById(productInfo);

        return imgUploadVM;
    }

    /**、
     * 插入或更新产品信息
     * @param productInfoModel
     */
    @Override
    public PutProductInfoVO putProductInfo(ProductInfoModel productInfoModel) {

        ProductInfo productInfo = productInfoService.selectOne(new EntityWrapper<ProductInfo>().eq("id",productInfoModel.getId()).last("Limit 1"));
        PutProductInfoVO putProductInfoVO = new PutProductInfoVO();
        if(productInfo == null || productInfoModel.getId().equals("")){
            ProductInfo productInfo1 = new ProductInfo();
            productInfo1.setId(baseMapper.selectLastId() + 1);
            putProductInfoVO.setProductId(String.valueOf(productInfo1.getId()));
            productInfo1.setGoodsname(productInfoModel.getProductName());
            productInfo1.setPrice(Float.valueOf(productInfoModel.getPrice()));
            productInfo1.setProductCount(Integer.valueOf(productInfoModel.getProductNum()));
            productInfo1.setStyle(productInfoModel.getStyle());
            putProductInfoVO.setStyle(productInfo1.getStyle());
            productInfo1.setKind(productInfoModel.getStyle());
            productInfo1.setSocer(productInfoModel.getSocer());
            productInfo1.setEditTime(new Date());
            productInfo1.setEditBy(Constants.ADMIN);
            productInfo1.setDeleteFlag(productInfoModel.getStatus());
            productInfoService.insert(productInfo1);
        }else{
            putProductInfoVO.setProductId(productInfoModel.getId());
            if(productInfoModel.getProductName() != null){
                productInfo.setGoodsname(productInfoModel.getProductName());
            }
            if(productInfoModel.getPrice() != null){
                productInfo.setPrice(Float.valueOf(productInfoModel.getPrice()));
            }
            if(productInfoModel.getProductNum() != null){
                productInfo.setProductCount(Integer.valueOf(productInfoModel.getProductNum()));
            }
            if(productInfoModel.getStyle() != null){
                productInfo.setStyle(productInfoModel.getStyle());
                productInfo.setKind(productInfoModel.getStyle());
                putProductInfoVO.getStyle();
            }
            if(productInfoModel.getSocer() != null){
                productInfo.setSocer(productInfoModel.getSocer());
            }
            if(productInfoModel.getStatus() != null){
                productInfo.setDeleteFlag(productInfoModel.getStatus());
            }
            productInfo.setEditTime(new Date());
            productInfoService.updateById(productInfo);
        }
        return putProductInfoVO;
    }

    @Override
    public void insertProductInfo(MultipartFile file,ProductInfoModel productInfoModel) throws Exception {

        String path = null;
        if(productInfoModel.getStyle().equals("0")){
            path = boxProductUploadDir;
        }else if(productInfoModel.getStyle().equals("1")){
            path = socerProductUploadDir;
        }else if(productInfoModel.getStyle().equals("2")){
            path = shopPicUploadDir;
        }
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(baseMapper.selectLastId() + 1);
        productInfo.setGoodsname(productInfoModel.getProductName());
        productInfo.setPrice(Float.valueOf(productInfoModel.getPrice()));
        ImgUploadVM imgUploadVM = UploadUtil.upload(file,path);
        productInfo.setImg(imgUploadVM.getImgFullName());
        productInfo.setProductCount(Integer.valueOf(productInfoModel.getProductNum()));
        productInfo.setStyle(productInfoModel.getStyle());
        productInfo.setKind(productInfoModel.getStyle());
        productInfo.setSocer(productInfoModel.getSocer());
        productInfo.setEditTime(new Date());
        productInfo.setEditBy(Constants.ADMIN);
        productInfo.setDeleteFlag(productInfoModel.getStatus());
        productInfoService.insert(productInfo);
    }


    /**
     * 获取数据详情
     */
    @Override
    public DataVO getInfoData() {

        // 获取销售总量
        SellCountModel sellCountModel = new SellCountModel();
        sellCountModel.setSellCount(baseMapper.getSellCount());
        // 获取每日销量
        List<DaysSellCountModel> daysSellCountModel = baseMapper.getDaysSellCount();
        // 获取盒子商品销售总量
        Integer BoxProductSellCount = shopLogService.selectCount(new EntityWrapper<ShopLog>().eq("boxId",Constants.BOX));
        // 获取积分商品销售总量
        Integer SocerProductSellCount = shopLogService.selectCount(new EntityWrapper<ShopLog>().eq("boxId",Constants.SHOP));
        // 获取邮回家商品数量
        Integer MailToHomeProductCount = shopLogService.selectCount(new EntityWrapper<ShopLog>().eq("express",Constants.YOU_HUI_JIA));
        // 获取换猩币商品数量
        Integer ChangeXingBiProduct = shopLogService.selectCount(new EntityWrapper<ShopLog>().eq("express",Constants.HUAN_XING_BI));
        // 获取待定商品数量
        Integer DaiDingProduct = shopLogService.selectCount(new EntityWrapper<ShopLog>().eq("express",Constants.ZAN_DING));
        // 获取销售历史总金额
        Integer SellAmountHistory = baseMapper.sellAmountHistory();
        // 获取用费总金额
        Integer ZIPAmountHistory = baseMapper.ZIPAmountHistory();
        // Model
        SellDataModel sellDataModel = new SellDataModel();
        sellDataModel.setBoxProductSellCount(BoxProductSellCount);
        sellDataModel.setChangeXingBiProduct(ChangeXingBiProduct);
        sellDataModel.setDaiDingProduct(DaiDingProduct);
        sellDataModel.setMailToHomeProductCount(MailToHomeProductCount);
        sellDataModel.setSellAmountHistory(SellAmountHistory);
        sellDataModel.setSocerProductSellCount(SocerProductSellCount);
        sellDataModel.setZIPAmountHistory(ZIPAmountHistory);
        // 获取每日销售总额
        List<EveryDaySellAmountModel> everyDaySellAmountModelList = baseMapper.everyDaySellAmount();
        for(ListIterator<EveryDaySellAmountModel> iterator = everyDaySellAmountModelList.listIterator();iterator.hasNext();){
            EveryDaySellAmountModel everyDaySellAmountModel = iterator.next();
            if(everyDaySellAmountModel.getAmount() == null){
                everyDaySellAmountModel.setAmount("0");
            }
            if(everyDaySellAmountModel.getZIPAmount() == null){
                everyDaySellAmountModel.setZIPAmount("0");
            }
        }
        // VO
        DataVO dataVO = new DataVO();
        dataVO.setSellDataModel(sellDataModel);
        dataVO.setSellCountModel(sellCountModel);
        dataVO.setEveryDaySellAmountModelList(everyDaySellAmountModelList);
        dataVO.setDaysSellCountModelList(daysSellCountModel);

        return dataVO;
    }

    /**
     * 更改产品状态
     * @param productId
     * @param status
     */
    @Override
    public void changeProductStatus(String productId, String status) {
        String deleteFlag = null;
        ProductInfo productInfo = productInfoService.selectOne(new EntityWrapper<ProductInfo>().eq("id",productId).last("Limit 1"));
        if(status.equals("0")){
            deleteFlag = "1";
        }else{
            deleteFlag = "0";
        }
        productInfo.setDeleteFlag(deleteFlag);
        productInfoService.updateById(productInfo);
    }


}
