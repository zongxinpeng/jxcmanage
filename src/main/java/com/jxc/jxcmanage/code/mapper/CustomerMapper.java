package com.jxc.jxcmanage.code.mapper;

import java.util.List;

import com.jxc.jxcmanage.code.entity.Customer;
import com.jxc.jxcmanage.dto.CustomerDto;

public interface CustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    // 批量写入
	int insertBatch(List<CustomerDto> params);
	//　分页查询
	List<CustomerDto> list(CustomerDto param);
}