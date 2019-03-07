package com.shopoline.xingyuanji.mapper;

import com.shopoline.xingyuanji.entity.Prize;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shopoline.xingyuanji.model.PrizeModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 奖品信息表 Mapper 接口
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
public interface PrizeMapper extends BaseMapper<Prize> {


    /**
     * 获取用户奖品列表
     * @param id
     * @return
     */
    List<PrizeModel> getPrizeList(@Param("id") Integer id);

}
