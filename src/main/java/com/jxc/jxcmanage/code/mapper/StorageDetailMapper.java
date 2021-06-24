package com.jxc.jxcmanage.code.mapper;

import java.util.List;
import com.jxc.jxcmanage.code.entity.StorageDetail;
import com.jxc.jxcmanage.dto.StorageDetailDto;

public interface StorageDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StorageDetail record);

    int insertSelective(StorageDetail record);

    StorageDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StorageDetail record);

    int updateByPrimaryKey(StorageDetail record);
	// 可供分页查询使用
	List<StorageDetailDto> list(StorageDetailDto param);
	// 批量插入，用于导入数据
	int insertBatch(List<StorageDetailDto> list);
}