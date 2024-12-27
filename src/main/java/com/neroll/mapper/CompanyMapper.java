package com.neroll.mapper;

import com.neroll.pojo.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyMapper {

    Company getCompanyById(@Param("id") Integer id);

    int saveCompany(Company company);

    int updateCompany(@Param("id") Integer id, @Param("company") Company company);

}
