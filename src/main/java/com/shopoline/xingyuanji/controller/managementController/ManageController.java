package com.shopoline.xingyuanji.controller.managementController;

import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.model.*;
import com.shopoline.xingyuanji.service.db1.IAdminInfoService;
import com.shopoline.xingyuanji.service.db1.IUserAddressService;
import com.shopoline.xingyuanji.service.db1.IUserAssetService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(description = "线上购物后台管理接口")
@Controller
@Scope("prototype")
@RequestMapping("/manage")
public class ManageController extends BaseController {

    @Autowired
    private IAdminInfoService adminInfoService;
    @Autowired
    private IUserAssetService userAssetService;
    @Autowired
    private IUserAddressService userAddressService;


    /**
     * 后台管理登陆
     * @param adminLoginModel
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "后台管理登陆" ,  notes="后台管理登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName" ,value ="用户名",required = true, dataType = "String"),
            @ApiImplicitParam(name = "passWord" ,value ="密码",required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/adminLogin")
    public Object adminLogin(AdminLoginModel adminLoginModel, HttpServletRequest request, HttpServletResponse response){
        JsonResult<String> json = new JsonResult<>();
        try{
            json.setData(adminInfoService.adminLogin(adminLoginModel));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 获取管理员信息
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "获取全部管理员信息" ,  notes="获取全部管理员信息")
    @ResponseBody
    @GetMapping("/getAdminInfoList")
    public Object getAdminInfoList(HttpServletRequest request, HttpServletResponse response){
        JsonResult<List<AdminInfoVO>> json = new JsonResult<>();
        try{
            json.setData(adminInfoService.getAdminInfoList());
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }


    /**
     * 增加或修改管理员信息
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "增加或修改管理员信息" ,  notes="增加或修改管理员信息")
    @ResponseBody
    @PostMapping("/privilegeManage")
    public Object privilegeManage(@RequestBody PrivilegeManageModel privilegeManageModel, HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try{
            adminInfoService.privilegeManage(privilegeManageModel);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }


    /**
     * 获取用户信息列表(包含条件查询)
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "获取用户信息列表(包含条件查询)" ,  notes="获取用户信息列表(包含条件查询)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickName" ,value ="ticketId",required = true, dataType = "String"),
            @ApiImplicitParam(name = "openId" ,value ="用户标识",required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageNum" ,value ="页码",required = true, dataType = "String")
    })
    @ResponseBody
    @GetMapping("/getUserInfoList")
    public Object getUserInfoList(String nickName,String openId,String pageNum,HttpServletRequest request, HttpServletResponse response){
        JsonResult<UserInfoListVO> json = new JsonResult<>();
        try{
            json.setData(adminInfoService.getUserInfoList(nickName,openId,pageNum));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }


    /**
     * 查询用户资产列表
     * @param userId
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "查询用户资产列表" ,  notes="查询用户资产列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId" ,value ="用户id",required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageNum" ,value ="页码",required = true, dataType = "String")
    })
    @ResponseBody
    @GetMapping("/getUserAssetLog")
    public Object getUserAssetLog(String userId,String pageNum,HttpServletRequest request, HttpServletResponse response){
        JsonResult<UserAssetListVO> json = new JsonResult<>();
        try{
            json.setData(adminInfoService.getUserAssetList(userId,pageNum));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }


    /**
     * 更新用户资产状态
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "更新用户资产状态" ,  notes="更新用户资产状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "amountId" ,value ="用户资产表id",required = true, dataType = "String"),
            @ApiImplicitParam(name = "deleteFlag" ,value ="启用状态0禁用 1启用",required = true, dataType = "String")
    })
    @ResponseBody
    @PutMapping("/replaceUserAsset")
    public Object replaceUserAsset(String amountId,String deleteFlag,HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try{
            adminInfoService.replaceUserAsset(amountId,deleteFlag);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }


    /**
     * 获取用户地址信息
     * @param userId
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "获取用户地址信息" ,  notes="获取用户地址信息")
    @ApiImplicitParam(name = "userId" ,value ="用户id",required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/getUserAddressList")
    public Object getUserAddressList(String userId,HttpServletRequest request, HttpServletResponse response){
        JsonResult<List<UserAddressInfoModel>> json = new JsonResult<>();
        try{
            json.setData(adminInfoService.getUserAddressInfoList(userId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 删除用户地址
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "删除用户地址" ,  notes="删除用户地址")
    @ApiImplicitParam(name = "addressId" ,value ="用户地址id",required = true, dataType = "String")
    @ResponseBody
    @DeleteMapping("/deleteUserAddress")
    public Object deleteUserAddress(String addressId,HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try{
            adminInfoService.deleteUserAddress(addressId);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 变更默认地址
     * @param addressId
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "删除用户地址" ,  notes="删除用户地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId" ,value ="用户地址id",required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId" ,value ="用户id",required = true, dataType = "String")
    })
    @ResponseBody
    @PutMapping("/changeUserDefAddress")
    public Object changeUserDefAddress(String addressId,String userId,HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try{
            adminInfoService.changeUserDefAddress(addressId,userId);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 变更或增加用户地址信息
     * @param changeUserAddressInfoModel
     * @return
     */
    @ApiOperation(value = "变更或增加用户地址信息" ,  notes="变更或增加用户地址信息")
    @ResponseBody
    @PostMapping("/changeUserAddressInfo")
    public Object changeUserAddressInfo(@RequestBody ChangeUserAddressInfoModel changeUserAddressInfoModel){
        JsonResult<Object> json = new JsonResult<>();
        try{
            adminInfoService.changeUserAddressInfo(changeUserAddressInfoModel);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     *  获取用户购买记录
     * @param openId
     * @return
     */
    @ApiOperation(value = "获取用户购买记录" ,  notes="获取用户购买记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId" ,value ="用户标识",required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageNum" ,value ="页码",required = true, dataType = "String")
    })
    @ResponseBody
    @GetMapping("/getUserShopLogInfo")
    public Object getUserShopLogInfo(String openId,String pageNum){
        JsonResult<UserShopLogInfoVO> json = new JsonResult<>();
        try{
            json.setData(adminInfoService.getUserShopLogInfo(openId,pageNum));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 删除用户购买记录
     * @param shopLogId
     * @return
     */
    @ApiOperation(value = "删除用户购买记录" ,  notes="删除用户购买记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopLogId" ,value ="购买记录id",required = true, dataType = "String"),
            @ApiImplicitParam(name = "deleteFlag" ,value ="启用状态0禁用 1启用",required = true, dataType = "String")
    })
    @ResponseBody
    @PutMapping("/deleteShopLog")
    public Object deleteShopLog(String shopLogId,String deleteFlag){
        JsonResult<Object> json = new JsonResult<>();
        try{
            adminInfoService.deleteShopLog(shopLogId,deleteFlag);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 输入用户运单号+变更物流状态
     * @param shopLogId
     * @return
     */
    @ApiOperation(value = "输入用户运单号+变更物流状态" ,  notes="输入用户运单号+变更物流状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopLogId" ,value ="购买记录id",required = true, dataType = "String"),
            @ApiImplicitParam(name = "ZIPNum" ,value ="运单号",required = true, dataType = "String")
    })
    @ResponseBody
    @PutMapping("/inputUserWaybill")
    public Object inputUserWaybill(String shopLogId,String ZIPNum){
        JsonResult<Object> json = new JsonResult<>();
        try{
            adminInfoService.inputUserWaybill(shopLogId,ZIPNum);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 获取全部用户购买记录
     * @param pageNum
     * @return
     */
    @ApiOperation(value = "获取全部用户购买记录" ,  notes="获取全部用户购买记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum" ,value ="购买记录id",required = true, dataType = "String"),
            @ApiImplicitParam(name = "days" ,value ="天数，搜索几天内数据",required = true, dataType = "String"),
            @ApiImplicitParam(name = "nickName" ,value ="用户昵称，模糊查询",required = true, dataType = "String"),
            @ApiImplicitParam(name = "openId" ,value ="用户标识",required = true, dataType = "String"),
    })
    @ResponseBody
    @GetMapping("/getAllShopLog")
    public Object getAllShopLog(String pageNum,String days,String nickName,String openId){
        JsonResult<AllShopLogVO> json = new JsonResult<>();
        try{
            json.setData(adminInfoService.getAllShopLog(pageNum,days,nickName,openId));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 获取商品列表
     * @param productType
     * @return
     */
    @ApiOperation(value = "获取商品列表" ,  notes="获取商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum" ,value ="购买记录id",required = true, dataType = "String"),
            @ApiImplicitParam(name = "productType" ,value ="商品类型，1积分商品0盒子商品",required = true, dataType = "String")
    })
    @ResponseBody
    @GetMapping("/adminGetProductList")
    public Object adminGetProductList(String pageNum,String productType){
        JsonResult<AdminGetProductVO> json = new JsonResult<>();
        try{
            json.setData(adminInfoService.adminGetProductList(pageNum,productType));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 插入或更新产品信息
     * @param productInfoModel
     * @return
     */
    @ApiOperation(value = "插入或更新产品信息" ,  notes="插入或更新产品信息")
    @ResponseBody
    @PutMapping("/putProductInfo")
    public Object putProductInfo(@RequestBody ProductInfoModel productInfoModel){
        JsonResult<PutProductInfoVO> json = new JsonResult<>();
        try{
            json.setData(adminInfoService.putProductInfo(productInfoModel));
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 添加产品
     * @param productInfoModel
     * @return
     */
    @ApiOperation(value = "添加产品" ,  notes="添加产品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id" ,value ="购买记录id",required = true, dataType = "String"),
            @ApiImplicitParam(name = "productName" ,value ="商品名称，1积分商品0盒子商品",required = true, dataType = "String"),
            @ApiImplicitParam(name = "price" ,value ="价格",required = true, dataType = "String"),
            @ApiImplicitParam(name = "productNum" ,value ="商品数量",required = true, dataType = "String"),
            @ApiImplicitParam(name = "style" ,value ="商品类型， 0：盒子商品  1：积分商品 ",required = true, dataType = "String"),
            @ApiImplicitParam(name = "socer" ,value ="商品积分",required = true, dataType = "String"),
            @ApiImplicitParam(name = "status" ,value ="是否启用 0：否 1：是",required = true, dataType = "String")
    })
    @ResponseBody
    @RequestMapping(value = "/insertProductInfo",method = RequestMethod.POST)
    public Object insertProductInfo(@RequestParam(value = "file") MultipartFile file,ProductInfoModel productInfoModel,HttpServletRequest request,HttpServletResponse response){
        JsonResult<Object> json = new JsonResult<>();
        try{
            adminInfoService.insertProductInfo(file,productInfoModel);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

    /**
     * 更改产品状态
     * @return
     */
    @ApiOperation(value = "更改产品状态" ,  notes="更改产品状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId" ,value ="商品id",required = true, dataType = "String"),
            @ApiImplicitParam(name = "status" ,value ="是否启用 0：否 1：是",required = true, dataType = "String")
    })
    @ResponseBody
    @PutMapping("/changeProductStatus")
    public Object changeProductStatus(String productId,String status){
        JsonResult<Object> json = new JsonResult<>();
        try{
            adminInfoService.changeProductStatus(productId,status);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }
    /**
     * 获取数据详情
     * @return
     */
    @ApiOperation(value = "获取数据中心信息" ,  notes="获取数据中心信息")
    @ResponseBody
    @GetMapping("/getInfoData")
    public Object getInfoData(){
        JsonResult<DataVO> json = new JsonResult<>();
        try{
            json.setData(adminInfoService.getInfoData());
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }

}
