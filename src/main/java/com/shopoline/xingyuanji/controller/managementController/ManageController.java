package com.shopoline.xingyuanji.controller.managementController;

import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.common.JsonResult;
import com.shopoline.xingyuanji.controller.baseController.BaseController;
import com.shopoline.xingyuanji.model.AdminLoginModel;
import com.shopoline.xingyuanji.model.PrivilegeManageModel;
import com.shopoline.xingyuanji.service.db1.IAdminInfoService;
import com.shopoline.xingyuanji.utils.JSONUtil;
import com.shopoline.xingyuanji.vo.AdminInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("/manage")
public class ManageController extends BaseController {

    @Autowired
    private IAdminInfoService adminInfoService;


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
    public Object privilegeManage(PrivilegeManageModel privilegeManageModel, HttpServletRequest request, HttpServletResponse response){
        JsonResult<String> json = new JsonResult<>();
        try{
            adminInfoService.privilegeManage(privilegeManageModel);
        }catch (Exception e){
            logger.info(e.getMessage());
            json.setState(ExceptionEnum.getKeyByValue(e.getMessage()));
            json.setMessage(ExceptionEnum.getValueByKey(json.getState()));
        }
        return JSONUtil.toJSONString(json);
    }



}
