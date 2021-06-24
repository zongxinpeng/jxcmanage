package com.jxc.jxcmanage.code.mapper;

import java.util.List;
import com.jxc.jxcmanage.code.entity.Supplier;
import com.jxc.jxcmanage.dto.SupplierDto;

public interface SupplierMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
	// 供应商列表，用于分页
	List<SupplierDto> list(SupplierDto param);
	// 批量新增
	int insertBatch(List<SupplierDto> list);
}