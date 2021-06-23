package com.jxc.jxcmanage.mapper;


import java.util.List;
import java.util.Map;
import com.jxc.jxcmanage.entity.UserInfo;

public interface UserInfoMapper {

	List<UserInfo> queryUserList(Map<String, Object> param);

	int deleteUserInfoById(Integer id);

	int insertUserInfo(UserInfo userInfo);

	int updateUserInfoById(UserInfo userInfo);
}
