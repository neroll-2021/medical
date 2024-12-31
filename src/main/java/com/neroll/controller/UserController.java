package com.neroll.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.neroll.pojo.Result;
import com.neroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result<String> login(String username, String password) {
        if (StpUtil.isLogin())
            return Result.error("用户已登录");
        System.out.println(username + ", " + password);
        return userService.login(username, password);
    }

    @PostMapping("/logout")
    public Result<String> logout() {
        if (!StpUtil.isLogin())
            return Result.error("未登录");
        StpUtil.logout();
        return Result.success("已退出登录");
    }

}
