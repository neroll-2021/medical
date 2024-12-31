package com.neroll.service;

import cn.dev33.satoken.stp.StpUtil;
import com.neroll.mapper.UserMapper;
import com.neroll.pojo.Result;
import com.neroll.pojo.User;
import com.neroll.utils.MD5Utils;
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

        // 使用MD5散列密码
        String md5password = MD5Utils.stringToMD5(password);

        // 根据用户名和密码查找用户，若没找到，说明用户名或密码错误
        User user = userMapper.getUserByNameAndPassword(username, md5password);
        if (user == null)
            return Result.error("用户名或密码错误");

        // 生成 token
        StpUtil.login(user.getId());

        return Result.success("登录成功", user.getUname());
    }
}
