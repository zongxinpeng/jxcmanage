package com.jxc.jxcmanage.service;

import com.jxc.jxcmanage.pojo.User;

import java.util.List;

public interface UserService {
    List<User> selectALl();
    List<User> selectAllByXML();
    List<User> selectAllByXML1();
}
