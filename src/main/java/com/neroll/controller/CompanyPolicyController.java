package com.neroll.controller;

import com.neroll.pojo.CompanyPolicy;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.service.CompanyPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/company_policy")
public class CompanyPolicyController {
    @Autowired
    private CompanyPolicyService service;

    private Result<CompanyPolicy> checkNonEmpty(CompanyPolicy policy) {
        if (policy == null)
            return Result.error("政策数据不能为空");

        if (!StringUtils.hasText(policy.getTitle()))
            return Result.error("政策标题不能为空");
        if (!StringUtils.hasText(policy.getMessage()))
            return Result.error("政策信息不能为空");
        if (policy.getCompanyId() == null)
            return Result.error("公司 id 不能为空");
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<CompanyPolicy> findPolicyById(@PathVariable Integer id) {
        if (id == null)
            return Result.error("政策 id 不能为空");

        return service.getCompanyPolicyById(id);
    }

    @GetMapping
    public Result<PageInfo<CompanyPolicy>> getCompanyPolicyByPage(@RequestParam("pn") Integer pageNum,
                                                                  @RequestParam("size") Integer pageSize,
                                                                  @RequestParam(value = "keyword", defaultValue = "")
                                                                          String keyword) {
        return service.getCompanyPolicyByPage(pageNum, pageSize, keyword);
    }

    @PostMapping
    public Result<CompanyPolicy> addCompanyPolicy(@RequestBody CompanyPolicy policy) {
        Result<CompanyPolicy> result = checkNonEmpty(policy);
        if (result.isError())
            return result;

        return service.addCompanyPolicy(policy);
    }

    @PutMapping("/{id}")
    public Result<CompanyPolicy> updateCompanyPolicy(@PathVariable Integer id, @RequestBody CompanyPolicy policy) {
        if (id == null)
            return Result.error("政策 id 不能为空");

        Result<CompanyPolicy> result = checkNonEmpty(policy);
        if (result.isError())
            return result;

        return service.updateCompanyPolicy(id, policy);
    }

    @DeleteMapping("/{id}")
    public Result<CompanyPolicy> deleteCompanyPolicy(@PathVariable Integer id) {
        if (id == null)
            return Result.error("政策 id 不能为空");

        return service.deleteCompanyPolicyById(id);
    }
}
