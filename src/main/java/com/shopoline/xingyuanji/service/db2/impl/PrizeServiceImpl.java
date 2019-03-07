package com.shopoline.xingyuanji.service.db2.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shopoline.xingyuanji.entity.Buyer;
import com.shopoline.xingyuanji.entity.Prize;
import com.shopoline.xingyuanji.mapper.PrizeMapper;
import com.shopoline.xingyuanji.model.PrizeModel;
import com.shopoline.xingyuanji.service.db2.IBuyerService;
import com.shopoline.xingyuanji.service.db2.IPrizeService;
import com.shopoline.xingyuanji.utils.GetOpenId;
import com.shopoline.xingyuanji.vo.PrizeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private IBuyerService buyerService;


    /**
     * 获取用户奖品列表
     * @param ticketId
     * @return
     */
    @Override
    public PrizeVO getPrizeList(String ticketId) {

        String openId = GetOpenId.getOpenId(ticketId);
        // 获取用户信息
        Buyer buyer = buyerService.selectOne(new EntityWrapper<Buyer>().eq("open_id",openId));
        //获取抽奖列表
        List<PrizeModel> prizeModelList = baseMapper.getPrizeList(buyer.getId());
        for(PrizeModel prizeModel:prizeModelList){
            prizeModel.setImg(prizeModel.getPrizeId()+".jpg");
        }
        // 写入VO
        PrizeVO prizeVO = new PrizeVO();
        prizeVO.setPrizeList(prizeModelList);
        return prizeVO;
    }
}
