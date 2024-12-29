package com.neroll.controller;

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
        System.out.println(username + ", " + password);
        return userService.login(username, password);
    }
}
