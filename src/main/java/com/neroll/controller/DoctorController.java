package com.neroll.controller;

import com.neroll.pojo.DoctorInfo;
import com.neroll.pojo.Result;
import com.neroll.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService service;

    @GetMapping
    public Result<DoctorInfo> searchDoctorByLevel(Integer pn, Integer size, String keyword) {
        if (pn == null)
            return Result.error("页码不能为空");
        if (pn <= 0)
            return Result.error("页码错误");
        if (size == null)
            return Result.error("页大小不能为空");
        if (size <= 0)
            return Result.error("页大小错误");

        return service.searchDoctorByLevel(pn, size, keyword);
    }
}
