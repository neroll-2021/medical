package com.neroll.controller;

import com.neroll.pojo.CompanyPolicy;
import com.neroll.pojo.Result;
import com.neroll.service.CompanyPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company_policy")
public class CompanyPolicyController {
    @Autowired
    private CompanyPolicyService service;

    @GetMapping("/{id}")
    public Result<CompanyPolicy> findPolicyById(@PathVariable Integer id) {
        if (id == null)
            return Result.error("政策 id 不能为空");

        return service.getCompanyPolicyById(id);
    }
}
