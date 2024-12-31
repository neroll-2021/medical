package com.neroll.controller;

import com.neroll.pojo.Doctor;
import com.neroll.pojo.DoctorVo;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService service;

    @GetMapping
    public Result<PageInfo<DoctorVo>> searchDoctorByLevel(@RequestParam("pn") Integer pn,
                                                          @RequestParam("size") Integer size,
                                                          @RequestParam(value = "keyword", defaultValue = "")
                                                                      String keyword) {
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

    @PostMapping
    public Result<Doctor> addDoctor(@RequestBody Doctor doctor) {
        if (doctor == null)
            return Result.error("医师数据不能为空");

        return service.addDoctor(doctor);
    }
}
