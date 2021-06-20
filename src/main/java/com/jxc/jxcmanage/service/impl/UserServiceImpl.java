package com.jxc.jxcmanage.service.impl;

import com.jxc.jxcmanage.mapper.UserMapper;
import com.jxc.jxcmanage.pojo.User;
import com.jxc.jxcmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectALl() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> selectAllByXML() {
        return userMapper.selectAllByXML();
    }

    @Override
    public List<User> selectAllByXML1() {
        return userMapper.selectAllByXML1();
    }
}
