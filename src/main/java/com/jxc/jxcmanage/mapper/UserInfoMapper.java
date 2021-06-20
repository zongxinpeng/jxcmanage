package com.jxc.jxcmanage.mapper;

import com.jxc.jxcmanage.pojo.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {

    List<UserInfo> queryUserList(Map<String,Object> param);
}
