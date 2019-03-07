package com.shopoline.xingyuanji.service.db2.impl;

import com.shopoline.xingyuanji.entity.User;
import com.shopoline.xingyuanji.mapper.UserMapper;
import com.shopoline.xingyuanji.service.db2.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
