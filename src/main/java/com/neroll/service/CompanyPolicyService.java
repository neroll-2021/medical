package com.neroll.service;

import com.neroll.mapper.CompanyMapper;
import com.neroll.mapper.CompanyPolicyMapper;
import com.neroll.pojo.Company;
import com.neroll.pojo.CompanyPolicy;
import com.neroll.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompanyPolicyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyPolicyMapper policyMapper;

    public Result<CompanyPolicy> getCompanyPolicyById(Integer id) {
        CompanyPolicy policy = policyMapper.getPolicyById(id);

        if (policy == null)
            return Result.error("政策不存在");

        return Result.success("查询成功", policy);
    }

    public Result<CompanyPolicy> addCompanyPolicy(CompanyPolicy policy) {
        Company company = companyMapper.getCompanyById(policy.getCompanyId());
        if (company == null)
            return Result.error("公司不存在");

        policy.setCreateTime(LocalDateTime.now());
        policy.setUpdateTime(LocalDateTime.now());

        int line = policyMapper.savePolicy(policy);
        if (line == 0)
            return Result.error("添加失败");
        return Result.success("添加成功");
    }
}
