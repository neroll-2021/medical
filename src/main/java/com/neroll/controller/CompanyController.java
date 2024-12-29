package com.neroll.controller;

import com.neroll.pojo.Company;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService service;

    @GetMapping
    public Result<PageInfo<Company>> getCompaniesByNameLike(@RequestParam("pn") Integer pageNum,
                                                            @RequestParam("size") Integer pageSize,
                                                            @RequestParam(value = "keyword", defaultValue = "")
                                                                    String name) {
        return service.findCompaniesByNameLike(pageNum, pageSize, name);
    }

    @GetMapping("/{id}")
    public Result<Company> findCompanyById(@PathVariable Integer id) {
        if (id == null)
            return Result.error("公司 id 不能为空");
        return service.findCompanyById(id);
    }

    @PostMapping
    public Result<Company> addCompany(@RequestBody Company company) {
        if (company == null)
            return Result.error("公司数据不能为空");
        if (!StringUtils.hasText(company.getName()))
            return Result.error("公司名称不能为空");
        if (!StringUtils.hasText(company.getPhone()))
            return Result.error("公司电话不能为空");
        return service.addCompany(company);
    }

    @PostMapping("/{id}")
    public Result<Company> updateCompany(@PathVariable Integer id, @RequestBody Company company) {
        if (id == null)
            return Result.error("公司 id 不能为空");
        if (company == null)
            return Result.error("公司数据不能为空");
        if (!StringUtils.hasText(company.getName()))
            return Result.error("公司名称不能为空");
        if (!StringUtils.hasText(company.getPhone()))
            return Result.error("公司电话不能为空");

        return service.updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    public Result<Company> deleteCompany(@PathVariable Integer id) {
        if (id == null)
            return Result.error("公司 id 不能为空");
        return service.deleteCompanyById(id);
    }
}
