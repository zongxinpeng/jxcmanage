package com.jxc.jxcmanage.code.mapper;

import java.util.List;

import com.jxc.jxcmanage.code.entity.SysDepartment;
import com.jxc.jxcmanage.dto.SysDepartmentDto;

public interface SysDepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDepartment record);

    int insertSelective(SysDepartment record);

    SysDepartment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDepartment record);

    int updateByPrimaryKey(SysDepartment record);
    // 用于分页查询
	List<SysDepartmentDto> list(SysDepartmentDto param);
}