package com.jxc.jxcmanage.service;

import com.jxc.jxcmanage.code.entity.UserInfo;

import java.util.Map;

public interface UserInfoService {
    Map<String ,Object> queryUserList(Map<String,Object> param);
    int deleteUserInfoById(Integer id);
    int addOrUpdateUserInfo(UserInfo userInfo);
}
