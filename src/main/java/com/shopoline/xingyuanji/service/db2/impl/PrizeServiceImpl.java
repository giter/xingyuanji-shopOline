package com.shopoline.xingyuanji.service.db2.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shopoline.RedPacketConfig;
import com.shopoline.xingyuanji.Constants;
import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.entity.UserAddress;
import com.shopoline.xingyuanji.entity.UserInfo;
import com.shopoline.xingyuanji.entity.Buyer;
import com.shopoline.xingyuanji.entity.Prize;
import com.shopoline.xingyuanji.entity.PrizeLog;
import com.shopoline.xingyuanji.mapper.PrizeMapper;
import com.shopoline.xingyuanji.model.PrizeModel;
import com.shopoline.xingyuanji.model.SendRedPackageModel;
import com.shopoline.xingyuanji.service.db1.IUserAddressService;
import com.shopoline.xingyuanji.service.db1.IUserInfoService;
import com.shopoline.xingyuanji.service.db2.IBuyerService;
import com.shopoline.xingyuanji.service.db2.IPrizeLogService;
import com.shopoline.xingyuanji.service.db2.IPrizeService;
import com.shopoline.xingyuanji.utils.SendRedPackageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>
 * 奖品信息表 服务实现类
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
@Service
public class PrizeServiceImpl extends ServiceImpl<PrizeMapper, Prize> implements IPrizeService {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private IBuyerService buyerService;
    @Autowired
    private IPrizeLogService prizeLogService;
    @Autowired
    private IUserAddressService userAddressService;
    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 获取用户奖品列表
     * @param ticketId
     * @return
     */
    @Override
    public List<PrizeModel> getPrizeList(String ticketId) {

        // 获取用户信息
        Buyer buyer = buyerService.getDB2UserInfo(ticketId);
        //获取抽奖列表
        List<PrizeModel> prizeList = baseMapper.getPrizeList(buyer.getId());
        // 遍历List写入图片信息
        for(ListIterator<PrizeModel> iterator = prizeList.listIterator();iterator.hasNext();){
            PrizeModel prizeModel = iterator.next();
            prizeModel.setImg(prizeModel.getPrizeId()+".jpg");
        }
        return prizeList;
    }

    /**
     * 用户兑奖
     * @param ticketId
     * @param prizeId
     * @return
     */
    @Override
    public String cashPrize(String ticketId, String prizeId, HttpServletRequest request) throws Exception {

            // 获取用户信息
            Buyer buyer = buyerService.getDB2UserInfo(ticketId);
            // 获取奖品信息
            PrizeLog prizeLog = prizeLogService.selectOne(new EntityWrapper<PrizeLog>().eq("id",prizeId).last("Limit 1"));
            // 判断是否是红包,如果是，则直接兑换红包
            if (prizeLog.getPrizeId()== RedPacketConfig.REDPACKET_88 ||
                    prizeLog.getPrizeId()== RedPacketConfig.REDPACKET_36_6||
                    prizeLog.getPrizeId()== RedPacketConfig.REDPACKET_18_8 ||
                    prizeLog.getPrizeId()== RedPacketConfig.REDPACKET_16_6||
                    prizeLog.getPrizeId()== RedPacketConfig.REDPACKET_8_8||
                    prizeLog.getPrizeId()== RedPacketConfig.REDPACKET_6_6||
                    prizeLog.getPrizeId()== RedPacketConfig.REDPACKET_1_88||
                    prizeLog.getPrizeId()== RedPacketConfig.REDPACKET_1_66||
                    prizeLog.getPrizeId()== RedPacketConfig.REDPACKET_0_88||
                    prizeLog.getPrizeId()== RedPacketConfig.REDPACKET_0_66
            ){
                // Log
                logger.info("<-红包兑换->"+"\t用户昵称："+buyer.getNickName()+"\t红包信息："+prizeLog.getPrizeId()+"\tDate："+ new Date());
                // 写入红包兑换信息
                SendRedPackageModel sendRedPackageModel = new SendRedPackageModel();
                sendRedPackageModel.setOpenId(buyer.getOpenId());
                sendRedPackageModel.setPrizeId(String.valueOf(prizeLog.getPrizeId()));
                sendRedPackageModel.setPrice(String.valueOf(prizeLog.getPrice()));
                sendRedPackageModel.setRequest(request);
                // 兑换红包
                SendRedPackageUtil sendRedPackage = new SendRedPackageUtil();
                String result = sendRedPackage.sendRedPackage(sendRedPackageModel);
                //判断微信接口返回值
                if(result.equals("SUCCESS")){
                    //如果成功从priceLog更新相应信息（逻辑删除将status设为未启用）
                    prizeLog.setUseflag(0);
                    prizeLogService.updateById(prizeLog);
                    // Log
                    logger.info("<-红包兑换->"+"\t用户昵称："+buyer.getNickName()+"\t红包信息："+prizeLog.getPrizeId()+"\tDate："+ new Date()+result);
                    return "兑换成功";
                }else {
                    throw new Exception(ExceptionEnum.EXCEPTION_22.getDesc());
                }
            }
            // 获取用户默认地址
            UserInfo userInfo = userInfoService.selectOne(new EntityWrapper<UserInfo>().eq("openId",buyer.getOpenId()).last("Limit 1"));
            UserAddress userAddress = userAddressService.selectOne(new EntityWrapper<UserAddress>().
                    eq("userId",userInfo.getUserId()).last("Limit 1"));
            prizeLog.setPostCode("0");
            prizeLog.setProvince(userAddress.getProvince());
            prizeLog.setCity(userAddress.getCity());
            prizeLog.setAddress(userAddress.getAddress());
            prizeLog.setAdname(userAddress.getName());
            prizeLog.setAdphone(userAddress.getPhone());
            prizeLog.setStatus(Constants.DB2_YIDUIJAING);
            prizeLog.setUpdateId(0);
            prizeLog.setUpdateTime(new Date());
            prizeLogService.updateById(prizeLog);
            // log
            logger.info("<-奖品兑换->："+prizeLog);

            return "兑换成功";
    }







}
