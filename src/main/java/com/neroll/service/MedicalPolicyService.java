package com.neroll.service;

import com.neroll.mapper.MedicalPolicyMapper;
import com.neroll.pojo.MedicalPolicy;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalPolicyService {
    @Autowired
    private MedicalPolicyMapper mapper;

    public Result<PageInfo<MedicalPolicy>> getPolicyByPage(Integer pageNum, Integer pageSize) {
        if (pageNum <= 0)
            return Result.error("页码错误");
        if (pageSize <= 0)
            return Result.error("页大小错误");

        int offset = (pageNum - 1) * pageSize;
        int count = pageSize;

        List<MedicalPolicy> policies = mapper.getMedicalPolicyByPage(offset, count);
        if (policies == null)
            return Result.error("查询失败");
        PageInfo<MedicalPolicy> info = new PageInfo<>();

        int total = mapper.getMedicalPolicyNum();

        info.setTotal(total);
        info.setList(policies);
        return Result.success("查询成功", info);
    }
}
