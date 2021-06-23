package com.jxc.jxcmanage.code.mapper;

import com.jxc.jxcmanage.code.entity.MenuInfo;

import java.util.List;
import java.util.Map;

public interface MenuInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MenuInfo record);

    int insertSelective(MenuInfo record);

    MenuInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MenuInfo record);

    int updateByPrimaryKey(MenuInfo record);

    List<MenuInfo> queryMenuList(Map<String, Object> param);
}