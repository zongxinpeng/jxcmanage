package com.jxc.jxcmanage.code.mapper;

import java.util.List;

import com.jxc.jxcmanage.code.entity.ProfitAmount;
import com.jxc.jxcmanage.dto.ProfitAmountDto;

public interface ProfitAmountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProfitAmount record);

    int insertSelective(ProfitAmount record);

    ProfitAmount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProfitAmount record);

    int updateByPrimaryKey(ProfitAmount record);

	List<ProfitAmountDto> list(ProfitAmountDto param);
}