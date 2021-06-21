package com.jxc.jxcmanage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxc.jxcmanage.mapper.UserInfoMapper;
import com.jxc.jxcmanage.pojo.UserInfo;
import com.jxc.jxcmanage.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Map<String ,Object> queryUserList(Map<String, Object> param) {
        PageHelper.startPage((Integer)param.get("pageNumber"),(Integer) param.get("pageSize"));
        PageInfo<UserInfo> page = new PageInfo<>(userInfoMapper.queryUserList(param));

        Map<String ,Object> resultData = new HashMap<>();
        resultData.put("total",page.getTotal());
        resultData.put("rows",page.getList());
        return resultData;
    }

    @Override
    public int deleteUserInfoById(Integer id) {
        return userInfoMapper.deleteUserInfoById(id);
    }

    @Override
    public int insertUserInfo(UserInfo userInfo) {
        return userInfoMapper.insertUserInfo(userInfo);
    }

    @Override
    public int updateUserInfoById(UserInfo userInfo) {
        return userInfoMapper.updateUserInfoById(userInfo);
    }
}
