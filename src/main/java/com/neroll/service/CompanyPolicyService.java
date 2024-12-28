package com.neroll.service;

import com.neroll.mapper.CompanyPolicyMapper;
import com.neroll.pojo.CompanyPolicy;
import com.neroll.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyPolicyService {
    @Autowired
    private CompanyPolicyMapper mapper;

    public Result<CompanyPolicy> getCompanyPolicyById(Integer id) {
        CompanyPolicy policy = mapper.getPolicyById(id);

        if (policy == null)
            return Result.error("政策不存在");

        return Result.success("查询成功", policy);
    }
}
