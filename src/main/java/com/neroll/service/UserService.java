package com.neroll.service;

import com.neroll.mapper.UserMapper;
import com.neroll.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Result<String> login(String username, String password) {
        // 非空校验
        if (!StringUtils.hasText(username))
            return Result.error("用户名不能为空");
        if (!StringUtils.hasText(password))
            return Result.error("密码不能为空");





        return Result.success();
    }
}
