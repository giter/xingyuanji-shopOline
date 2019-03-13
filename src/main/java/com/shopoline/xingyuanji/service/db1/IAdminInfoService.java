package com.shopoline.xingyuanji.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.shopoline.xingyuanji.entity.AdminInfo;
import com.shopoline.xingyuanji.model.AdminLoginModel;

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

}
