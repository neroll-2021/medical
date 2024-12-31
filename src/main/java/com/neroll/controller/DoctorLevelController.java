package com.neroll.controller;

import com.neroll.pojo.DoctorLevel;
import com.neroll.pojo.Result;
import com.neroll.service.DoctorLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor_level")
public class DoctorLevelController {
    @Autowired
    private DoctorLevelService service;

    @GetMapping
    public Result<List<DoctorLevel>> getAllDoctorLevel() {
        return service.getAllDoctorLevel();
    }
}
