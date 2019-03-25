package com.shopoline.xingyuanji.service.db2.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shopoline.RedPacketConfig;
import com.shopoline.xingyuanji.Config;
import com.shopoline.xingyuanji.Constants;
import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.entity.*;
import com.shopoline.xingyuanji.mapper.PrizeMapper;
import com.shopoline.xingyuanji.model.PrizeModel;
import com.shopoline.xingyuanji.model.SendRedPackageModel;
import com.shopoline.xingyuanji.service.db1.IUserAddressService;
import com.shopoline.xingyuanji.service.db1.IUserInfoService;
import com.shopoline.xingyuanji.service.db2.IBuyerService;
import com.shopoline.xingyuanji.service.db2.IPrizeCodeService;
import com.shopoline.xingyuanji.service.db2.IPrizeLogService;
import com.shopoline.xingyuanji.service.db2.IPrizeService;
import com.shopoline.xingyuanji.utils.QRCodeUtils;
import com.shopoline.xingyuanji.utils.RedisUtil;
import com.shopoline.xingyuanji.utils.SendRedPackageUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    @Autowired
    private IPrizeCodeService prizeCodeService;

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
    public synchronized String cashPrize(String ticketId, String prizeId, HttpServletRequest request) throws Exception {

        // 获取用户信息
        Buyer buyer = buyerService.getDB2UserInfo(ticketId);

        boolean tokenResult = RedisUtil.hasKey("RED_PACKAGE"+buyer.getOpenId());
        if( tokenResult == true ){
            logger.info("<-RED_PACKAGE->:"+buyer.getNickName()+"\tBUYER_TOKEN："+buyer.getOpenId());
            throw new Exception(ExceptionEnum.EXCEPTION_24.getDesc());
        }
        // 获取奖品信息
        PrizeLog prizeLog = prizeLogService.selectOne(new EntityWrapper<PrizeLog>().eq("id", prizeId).
                eq("useflag",Constants.QIYONG).last("Limit 1"));
        // 判断是否是红包,如果是，则直接兑换红包
        if (prizeLog.getPrizeId() == RedPacketConfig.REDPACKET_88 ||
                prizeLog.getPrizeId() == RedPacketConfig.REDPACKET_36_6 ||
                prizeLog.getPrizeId() == RedPacketConfig.REDPACKET_18_8 ||
                prizeLog.getPrizeId() == RedPacketConfig.REDPACKET_16_6 ||
                prizeLog.getPrizeId() == RedPacketConfig.REDPACKET_8_8 ||
                prizeLog.getPrizeId() == RedPacketConfig.REDPACKET_6_6 ||
                prizeLog.getPrizeId() == RedPacketConfig.REDPACKET_1_88 ||
                prizeLog.getPrizeId() == RedPacketConfig.REDPACKET_1_66 ||
                prizeLog.getPrizeId() == RedPacketConfig.REDPACKET_0_88 ||
                prizeLog.getPrizeId() == RedPacketConfig.REDPACKET_0_66
        ) {
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
            if (result.equals("SUCCESS")) {
                //如果成功从priceLog更新相应信息（逻辑删除将status设为未启用）
                prizeLog.setUseflag(0);
                prizeLogService.updateById(prizeLog);
                logger.info("<-红包兑换写入TOKEN->" + "\t用户昵称：" + buyer.getNickName() + "\t红包信息：" + prizeLog.getPrizeId() +
                        "\tREDIS_TOKEN:"+"RED_PACKAGE"+buyer.getOpenId()+"\tDate：" + new Date() + result);
                RedisUtil.setValue2("RED_PACKAGE"+buyer.getOpenId(),buyer.getOpenId());
                return "兑换成功";
            } else {
                throw new Exception(ExceptionEnum.EXCEPTION_22.getDesc());
            }
        }
        // 获取用户默认地址
        UserInfo userInfo = userInfoService.getRedPacketUserInfo(buyer.getOpenId());
        UserAddress userAddress = userAddressService.selectOne(new EntityWrapper<UserAddress>().
                eq("userId", userInfo.getUserId()).last("Limit 1"));
        prizeLog.setPostCode("0");
        prizeLog.setProvince(userAddress.getProvince());
        prizeLog.setCity(userAddress.getCity());
        prizeLog.setAddress(userAddress.getAddress());
        prizeLog.setAdname(userAddress.getName());
        prizeLog.setAdphone(userAddress.getPhone());
        prizeLog.setStatus(Constants.DB2_YIDUIJAING);
        prizeLog.setUpdateId(0);
        prizeLog.setUpdateTime(new Date());
        prizeLog.setUseflag(Constants.JINYONG);
        prizeLogService.updateByInfo(prizeLog);
        // log
        logger.info("<-奖品兑换->：" + prizeLog);

        return "兑换成功";
    }

    @Override
    public void saveCode(int num) {

        List<PrizeCode> list = new LinkedList<>();
        int id = (int) (System.currentTimeMillis()/1000);
        for (int i=0;i<num;i++){
            PrizeCode prizeCode = new PrizeCode();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            uuid = new SimpleHash("md5",uuid).toHex();
            System.out.println(uuid);
            prizeCode.setImg("https://www.xingyuanji.com/wxp/towx/");
            prizeCode.setUid(uuid);
            prizeCode.setStatus(0);
            list.add(prizeCode);
            id=id+i;
            createQrImg(uuid,id);
            prizeCode.setId(id);
            if (i>9){
                prizeCodeService.save(list);
                list.clear();
                i=0;
                num=num-10;
            }
        }
        if (list.size()>0) {
            prizeCodeService.save(list);
        }
    }

    //    创建二维码
    private void createQrImg(String uid,Integer id){
        String url = Config.SERVER+"wxp/towx/"+uid;
        String savePath = Config.UPLOAD_DIR+id+".png";
        QRCodeUtils.createQRCode(url,savePath);
    }


}
