package com.jxc.jxcmanage.code.mapper;

import com.jxc.jxcmanage.code.entity.SaleOrder;

public interface SaleOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleOrder record);

    int insertSelective(SaleOrder record);

    SaleOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SaleOrder record);

    int updateByPrimaryKey(SaleOrder record);
}