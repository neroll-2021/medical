package com.neroll.controller;

import com.neroll.pojo.CompanyPolicy;
import com.neroll.pojo.Result;
import com.neroll.service.CompanyPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Result<CompanyPolicy> addCompanyPolicy(@RequestBody CompanyPolicy policy) {
        if (policy == null)
            return Result.error("政策数据不能为空");
        if (!StringUtils.hasText(policy.getTitle()))
            return Result.error("政策标题不能为空");
        if (!StringUtils.hasText(policy.getMessage()))
            return Result.error("政策信息不能为空");
        if (policy.getCompanyId() == null)
            return Result.error("公司 id 不能为空");

        return service.addCompanyPolicy(policy);
    }
}
