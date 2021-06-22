package com.jxc.jxcmanage.code.mapper;

import com.jxc.jxcmanage.code.entity.ProfitAmount;

public interface ProfitAmountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProfitAmount record);

    int insertSelective(ProfitAmount record);

    ProfitAmount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProfitAmount record);

    int updateByPrimaryKey(ProfitAmount record);
}