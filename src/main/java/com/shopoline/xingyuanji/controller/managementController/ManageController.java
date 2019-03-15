package com.shopoline.xingyuanji.controller.managementController;

import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.model.AdminLoginModel;
import com.shopoline.xingyuanji.model.PrivilegeManageModel;
import com.shopoline.xingyuanji.model.UserAddressInfoModel;
import com.shopoline.xingyuanji.service.db1.IAdminInfoService;
import com.shopoline.xingyuanji.service.db1.IUserAddressService;
import com.shopoline.xingyuanji.service.db1.IUserAssetService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.AdminInfoVO;
import com.shopoline.xingyuanji.vo.UserAssetListVO;
import com.shopoline.xingyuanji.vo.UserInfoListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
}
