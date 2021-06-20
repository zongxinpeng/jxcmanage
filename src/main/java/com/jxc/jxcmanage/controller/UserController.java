package com.jxc.jxcmanage.controller;

import com.jxc.jxcmanage.pojo.User;
import com.jxc.jxcmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    public Map getUser(@RequestParam(name = "userName",defaultValue = "站三") String userName){
        Map<String,String> map = new HashMap<>();
        map.put("name",userName);
        map.put("age","11");
        return map;
    }

    @RequestMapping("/findAllUser")
    public List<User> findAllUser(){
        return userService.selectALl();
    }

    @RequestMapping("/selectAllByXML")
    public List<User> selectAllByXML(){
        return userService.selectAllByXML();
    }

    @RequestMapping("/selectAllByXML1")
    public List<User> selectAllByXML1(){
        List<User> list = userService.selectAllByXML();
        log.info(String.valueOf(list));
        return list;
    }

}
