package com.neroll.service;

import com.neroll.mapper.CompanyMapper;
import com.neroll.mapper.CompanyPolicyMapper;
import com.neroll.pojo.Company;
import com.neroll.pojo.CompanyPolicy;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public Result<PageInfo<CompanyPolicy>> getCompanyPolicyByPage(Integer pageNum, Integer pageSize, String keyword) {
        if (pageNum <= 0)
            return Result.error("页码错误");
        if (pageSize <= 0)
            return Result.error("页大小错误");

        int offset = (pageNum - 1) * pageSize;
        int count = pageSize;

        String searchText = "%" + keyword + "%";

        List<CompanyPolicy> policies = policyMapper.getPolicyByPage(offset, count, searchText);
        if (policies == null)
            return Result.error("查询失败");

        int total = policyMapper.getPolicyCountNameLike(searchText);

        PageInfo<CompanyPolicy> info = new PageInfo<>();
        info.setTotal(total);
        info.setList(policies);
        return Result.success("查询成功", info);
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

    public Result<CompanyPolicy> updateCompanyPolicy(Integer id, CompanyPolicy policy) {
        Company company = companyMapper.getCompanyById(policy.getCompanyId());
        if (company == null)
            return Result.error("公司不存在");

        policy.setUpdateTime(LocalDateTime.now());

        int line = policyMapper.updatePolicy(id, policy);
        if (line == 0)
            return Result.error("政策不存在");
        return Result.success("修改成功");
    }

    public Result<CompanyPolicy> deleteCompanyPolicyById(Integer id) {
        int line = policyMapper.deletePolicyById(id);
        if (line == 0)
            return Result.error("政策不存在");
        return Result.success("删除成功");
    }
}
