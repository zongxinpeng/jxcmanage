package com.jxc.jxcmanage.mapper;

import com.jxc.jxcmanage.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("SELECT id , magic_id as 'magicId', first_name as 'firstName', last_name as 'lastName' FROM test")
    List<User> selectAll();

    List<User> selectAllByXML();

    List<User> selectAllByXML1();
}
