package com.jxc.jxcmanage.code.mapper;

import java.util.List;
import com.jxc.jxcmanage.code.entity.SaleDetail;
import com.jxc.jxcmanage.dto.SaleDetailDto;

public interface SaleDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleDetail record);

    int insertSelective(SaleDetail record);

    SaleDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SaleDetail record);

    int updateByPrimaryKey(SaleDetail record);
    // 用于分页查询
	List<SaleDetailDto> list(SaleDetailDto param);
    // 批量插入
	int insertBatch(List<SaleDetailDto> list);
}