package com.neroll.controller;

import com.neroll.pojo.MedicalPolicy;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.service.MedicalPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medical_policy")
public class MedicalPolicyController {
    @Autowired
    private MedicalPolicyService service;

    @GetMapping
    public Result<PageInfo<MedicalPolicy>> getPolicyByPage(@RequestParam("pn") Integer pageNum,
                                                           @RequestParam("size") Integer pageSize) {
        if (pageNum == null)
            return Result.error("页码不能为空");
        if (pageSize == null)
            return Result.error("页大小不能为空");
        return service.getPolicyByPage(pageNum, pageSize);
    }
}
