package com.shopoline.xingyuanji.mapper;

import com.shopoline.xingyuanji.entity.PrizeCode;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 奖品二维码表 Mapper 接口
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
public interface PrizeCodeMapper extends BaseMapper<PrizeCode> {

    void save(@Param("list") List<PrizeCode> list);

}
