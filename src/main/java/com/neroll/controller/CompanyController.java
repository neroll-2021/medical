package com.neroll.controller;

import com.neroll.pojo.Company;
import com.neroll.pojo.Result;
import com.neroll.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService service;

    @GetMapping("/{id}")
    public Result<Company> findCompanyById(@PathVariable Integer id) {
        if (id == null)
            return Result.error("公司 id 不能为空");
        return service.findCompanyById(id);
    }
}
