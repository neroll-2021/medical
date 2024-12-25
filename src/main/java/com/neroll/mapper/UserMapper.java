package com.neroll.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 根据用户名和密码获取用户
    void getUserByNameAndPassword(String username, String password);
}
