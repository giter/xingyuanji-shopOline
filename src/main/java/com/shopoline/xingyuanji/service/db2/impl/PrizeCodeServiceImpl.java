package com.shopoline.xingyuanji.service.db2.impl;

import com.shopoline.xingyuanji.entity.PrizeCode;
import com.shopoline.xingyuanji.mapper.PrizeCodeMapper;
import com.shopoline.xingyuanji.service.db2.IPrizeCodeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 奖品二维码表 服务实现类
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
@Service
public class PrizeCodeServiceImpl extends ServiceImpl<PrizeCodeMapper, PrizeCode> implements IPrizeCodeService {

    @Override
    public void save(List<PrizeCode> list) {
        baseMapper.save(list);
    }
}
