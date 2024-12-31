package com.neroll.mapper;

import com.neroll.pojo.Company;
import com.neroll.pojo.CompanyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyMapper {

    Company getCompanyById(@Param("id") Integer id);

    int saveCompany(Company company);

    int updateCompany(@Param("id") Integer id, @Param("company") Company company);

    int deleteCompanyById(@Param("id") Integer id);

    List<Company> getCompaniesByNameLike(@Param("offset") Integer offset,
                                         @Param("count") Integer count,
                                         @Param("name") String name);

    int getCompanyTotalCount();

    int getCompanyCountNameLike(@Param("name") String name);

    List<CompanyVo> getAllCompanyVo();

}
