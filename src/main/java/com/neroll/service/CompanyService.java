package com.neroll.service;

import com.neroll.mapper.CompanyMapper;
import com.neroll.pojo.Company;
import com.neroll.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompanyService {

    @Autowired
    private CompanyMapper mapper;

    public Result<Company> findCompanyById(Integer id) {
        Company company = mapper.getCompanyById(id);
        return Result.success("查找成功", company);
    }

    public Result<Company> addCompany(Company company) {
        company.setCreateTime(LocalDateTime.now());
        company.setUpdateTime(LocalDateTime.now());

        int line = mapper.saveCompany(company);
        if (line == 0)
            return Result.error("添加失败");
        return Result.success("添加成功");
    }
}
