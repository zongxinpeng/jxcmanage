package com.jxc.jxcmanage.code.mapper;

import com.jxc.jxcmanage.code.entity.SaleDetail;

public interface SaleDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleDetail record);

    int insertSelective(SaleDetail record);

    SaleDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SaleDetail record);

    int updateByPrimaryKey(SaleDetail record);
}