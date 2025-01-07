package com.neroll.controller;

import com.neroll.pojo.Doctor;
import com.neroll.pojo.DoctorVo;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService service;

    private Result<Doctor> checkNonEmpty(Doctor doctor) {
        if (doctor == null)
            return Result.error("医师数据不能为空");

        if (!StringUtils.hasText(doctor.getName()))
            return Result.error("医师姓名不能为空");
        if (doctor.getAge() == null)
            return Result.error("医师年龄不能为空");
        if (doctor.getSex() == null)
            return Result.error("医师性别不能为空");
        if (doctor.getLevelId() == null)
            return Result.error("医师级别不能为空");
        if (!StringUtils.hasText(doctor.getPhone()))
            return Result.error("医师电话不能为空");
        if (doctor.getTypeId() == null)
            return Result.error("医师治疗类别不能为空");
        if (!StringUtils.hasText(doctor.getHospital()))
            return Result.error("医师所属医院不能为空");
        if (doctor.getAccountId() == null)
            return Result.error("医师账号 id 不能为空");
        return Result.success();
    }

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
        Result<Doctor> checkResult = checkNonEmpty(doctor);
        if (checkResult.isError())
            return checkResult;

        return service.addDoctor(doctor);
    }

    @PutMapping("/{id}")
    public Result<Doctor> updateDoctor(@PathVariable Integer id, @RequestBody Doctor doctor) {
        Result<Doctor> checkResult = checkNonEmpty(doctor);
        if (checkResult.isError())
            return checkResult;

        return service.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public Result<Doctor> deleteDoctorById(@PathVariable Integer id) {
        return service.deleteDoctorById(id);
    }

    @GetMapping("/bar")
    public Result getBarData() {//获取数据面板的医生统计数据，柱状图
        return service.getBarData();
    }

    @GetMapping("/pie")
    public Result getPieData() {//获取数据面板的医生统计数据，饼状图
        return service.getPieData();
    }
}
