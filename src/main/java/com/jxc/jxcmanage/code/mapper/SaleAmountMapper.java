package com.jxc.jxcmanage.code.mapper;

import com.jxc.jxcmanage.code.entity.SaleAmount;

public interface SaleAmountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleAmount record);

    int insertSelective(SaleAmount record);

    SaleAmount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SaleAmount record);

    int updateByPrimaryKey(SaleAmount record);
}