package com.jxc.jxcmanage.code.mapper;

import java.util.List;

import com.jxc.jxcmanage.code.entity.SaleAmount;
import com.jxc.jxcmanage.dto.SaleAmountDto;

public interface SaleAmountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleAmount record);

    int insertSelective(SaleAmount record);

    SaleAmount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SaleAmount record);

    int updateByPrimaryKey(SaleAmount record);

	List<SaleAmountDto> list(SaleAmountDto param);
}