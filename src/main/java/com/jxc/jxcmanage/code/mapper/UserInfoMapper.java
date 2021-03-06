package com.jxc.jxcmanage.code.mapper;


import com.jxc.jxcmanage.code.entity.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {

	List<UserInfo> queryUserList(Map<String, Object> param);

	int deleteUserInfoById(Integer id);

	int insertUserInfo(UserInfo userInfo);

	int updateUserInfoById(UserInfo userInfo);
}
