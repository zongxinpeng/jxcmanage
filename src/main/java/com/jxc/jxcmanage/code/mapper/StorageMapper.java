package com.jxc.jxcmanage.code.mapper;

import java.util.List;
import com.jxc.jxcmanage.code.entity.Storage;
import com.jxc.jxcmanage.dto.StorageDto;

public interface StorageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Storage record);

    int insertSelective(Storage record);

    Storage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);
	// 可以分页查询
	List<StorageDto> list(StorageDto param);
}