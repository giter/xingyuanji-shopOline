package com.shopoline.xingyuanji.utils;

import com.shopoline.xingyuanji.Constants;
import com.shopoline.xingyuanji.vo.LoginVO;

public class CheckTicketIdUtil {

    public LoginVO checkTicketId(String openId){

        String ticketId = TicketUtil.getTicketId();
        if (RedisUtil.hasKey(openId)){
            RedisUtil.delete(RedisUtil.getValue(openId));
        }
        // 将TOKEN写入redis
        RedisUtil.setValueMinutes(ticketId, openId);
        RedisUtil.setValueMinutes(openId, ticketId);

        LoginVO loginVO = new LoginVO();
        loginVO.setTicketId(ticketId);
        loginVO.setCode(openId);
        loginVO.setChannelCode(Constants.CHANNEL_BOXOLINE);
        return loginVO;
    }
}
