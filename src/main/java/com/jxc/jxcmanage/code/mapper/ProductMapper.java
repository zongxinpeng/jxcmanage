package com.jxc.jxcmanage.code.mapper;

import java.util.List;
import com.jxc.jxcmanage.code.entity.Product;
import com.jxc.jxcmanage.dto.ProductDto;

public interface ProductMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Product record);

	int insertSelective(Product record);

	Product selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Product record);

	int updateByPrimaryKey(Product record);
	// 用于分页的查询
	List<ProductDto> list(ProductDto dto);
	// 批量插入，用于导入数据
	int insertBatch(List<ProductDto> list);
}