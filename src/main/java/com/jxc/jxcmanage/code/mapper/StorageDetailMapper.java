package com.jxc.jxcmanage.code.mapper;

import com.jxc.jxcmanage.code.entity.StorageDetail;

public interface StorageDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StorageDetail record);

    int insertSelective(StorageDetail record);

    StorageDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StorageDetail record);

    int updateByPrimaryKey(StorageDetail record);
}