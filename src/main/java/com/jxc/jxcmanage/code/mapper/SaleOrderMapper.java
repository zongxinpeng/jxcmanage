package com.jxc.jxcmanage.code.mapper;

import java.util.List;
import com.jxc.jxcmanage.code.entity.SaleOrder;
import com.jxc.jxcmanage.dto.SaleOrderDto;

public interface SaleOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleOrder record);

    int insertSelective(SaleOrder record);

    SaleOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SaleOrder record);

    int updateByPrimaryKey(SaleOrder record);
	// 可供分页查询使用
	List<SaleOrderDto> list(SaleOrderDto param);
}