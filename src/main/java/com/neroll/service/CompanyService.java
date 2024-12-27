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

    public Result<Company> updateCompany(Integer id, Company company) {
        boolean exist = mapper.getCompanyById(id) != null;
        if (!exist)
            return Result.error("公司不存在");

        company.setUpdateTime(LocalDateTime.now());

        int line = mapper.updateCompany(id, company);
        if (line == 0)
            return Result.error("更新失败");
        return Result.success("更新成功");
    }

    public Result<Company> deleteCompanyById(Integer id) {
        boolean exist = mapper.getCompanyById(id) != null;
        if (!exist)
            return Result.error("公司不存在");

        int line = mapper.deleteCompanyById(id);
        if (line == 0)
            return Result.error("删除失败");
        return Result.success("删除成功");
    }
}
