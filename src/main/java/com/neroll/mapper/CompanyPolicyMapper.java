package com.neroll.mapper;

import com.neroll.pojo.CompanyPolicy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyPolicyMapper {

    CompanyPolicy getPolicyById(@Param("id") Integer id);

    int savePolicy(CompanyPolicy policy);

    int updatePolicy(@Param("id") Integer id, @Param("policy") CompanyPolicy policy);

    int deletePolicyById(@Param("id") Integer id);
}
