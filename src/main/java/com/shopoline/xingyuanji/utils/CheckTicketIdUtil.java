package com.shopoline.xingyuanji.utils;

import com.shopoline.xingyuanji.Constants;
import com.shopoline.xingyuanji.vo.LoginVO;

public class CheckTicketIdUtil {

    public LoginVO checkTicketId(String openId){

        String ticketId = TicketUtil.getTicketId();
        // 如果Redis内存在当前用户的OpenId，删除
        if (RedisUtil.hasKey(openId)){
            RedisUtil.delete(RedisUtil.getValue(openId));
        }
        // 将TOKEN写入redis
        RedisUtil.setValueMinutes(ticketId, openId);
        RedisUtil.setValueMinutes(openId, ticketId);
        // 将ticket与OpenId写入VO
        LoginVO loginVO = new LoginVO();
        loginVO.setTicketId(ticketId);
        loginVO.setCode(openId);
        loginVO.setChannelCode(Constants.CHANNEL_BOXOLINE);
        return loginVO;
    }
}
