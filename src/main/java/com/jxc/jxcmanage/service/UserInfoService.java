package com.jxc.jxcmanage.service;

import com.jxc.jxcmanage.entity.UserInfo;

import java.util.Map;

public interface UserInfoService {
    String DEFAULT_BY = "SYSTEM";
    Map<String ,Object> queryUserList(Map<String,Object> param);
    int deleteUserInfoById(Integer id);
    int addOrUpdateUserInfo(UserInfo userInfo);
}
