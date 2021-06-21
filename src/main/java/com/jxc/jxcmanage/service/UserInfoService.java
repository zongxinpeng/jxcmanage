package com.jxc.jxcmanage.service;

import com.jxc.jxcmanage.pojo.UserInfo;

import java.util.Map;

public interface UserInfoService {
    String DEFAULT_BY = "SYSTEM";
    Map<String ,Object> queryUserList(Map<String,Object> param);
    int deleteUserInfoById(Integer id);
    int insertUserInfo(UserInfo userInfo);
    int updateUserInfoById(UserInfo userInfo);
}
