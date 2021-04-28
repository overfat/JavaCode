package com.hanfx.dao;

import com.hanfx.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    // 使用注解
//    @Select("select * from students")
    List<User> findAll();
}
