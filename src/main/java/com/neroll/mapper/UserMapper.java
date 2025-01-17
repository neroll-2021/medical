package com.neroll.mapper;

import com.neroll.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    // 根据用户名和密码获取用户
    User getUserByNameAndPassword(@Param("username") String username, @Param("password") String password);

    User getUserByAccountId(@Param("id") Integer id);

    User getUserByUsername(@Param("username") String username);

    int addUser(User user);

    int lastInsertId();
}
