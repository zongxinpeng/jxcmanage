package com.jxc.jxcmanage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.entity.UserInfo;
import com.jxc.jxcmanage.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userInfo")
@Slf4j
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户列表
     * @param param
     * @return
     */
    @PostMapping("/queryUserList")
    public ResultBean queryUserList(@RequestBody Map<String,Object> param) {
        String createdDateStart = (String)param.get("createdDateStart");
        String createdDateEnd = (String)param.get("createdDateEnd");
        if(StringUtils.hasLength(createdDateStart) && StringUtils.hasLength(createdDateEnd)){
            param.put("createdDateStart", createdDateStart + " 00:00:00");
            param.put("createdDateEnd", createdDateEnd + " 23:59:59");
        } else if(StringUtils.hasLength(createdDateStart) || StringUtils.hasLength(createdDateEnd)){
            return ResultBean.fail();
        }

        return ResultBean.success(userInfoService.queryUserList(param));
    }


    /**
     * 删除用户
     * @param param
     * @return
     */
    @PostMapping("/deleteUserInfoById")
    public ResultBean deleteUserInfoById(@RequestBody Map<String,Object> param){
        Integer id = (Integer) param.get("id");
        return ResultBean.success(userInfoService.deleteUserInfoById(id));
    }

    /**
     * 新增或者修改用户
     * @param userInfo
     * @return
     */
    @PostMapping("/addOrUpdateUserInfo")
    public ResultBean insertUserInfo(@RequestBody UserInfo userInfo) {
        return ResultBean.success(userInfoService.addOrUpdateUserInfo(userInfo));
    }

}
