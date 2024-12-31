package com.neroll.controller;

import com.neroll.pojo.Result;
import com.neroll.pojo.TreatType;
import com.neroll.service.TreatTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/treat_type")
public class TreatTypeController {
    @Autowired
    private TreatTypeService service;

    @GetMapping
    public Result<List<TreatType>> getAllTreatType() {
        return service.getAllTreatType();
    }
}
