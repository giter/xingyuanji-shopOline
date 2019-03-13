package com.shopoline.xingyuanji.service.db1.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shopoline.xingyuanji.Constants;
import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.entity.AdminInfo;
import com.shopoline.xingyuanji.mapper.AdminInfoMapper;
import com.shopoline.xingyuanji.model.AdminLoginModel;
import com.shopoline.xingyuanji.service.db1.IAdminInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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


}
