package com.shopoline.xingyuanji.service.db2;

import com.shopoline.xingyuanji.entity.PrizeCode;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 奖品二维码表 服务类
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
public interface IPrizeCodeService extends IService<PrizeCode> {


    void save(List<PrizeCode> list);
}
