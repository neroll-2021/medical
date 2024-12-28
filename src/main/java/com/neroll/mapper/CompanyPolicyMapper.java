package com.neroll.mapper;

import com.neroll.pojo.CompanyPolicy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyPolicyMapper {

    CompanyPolicy getPolicyById(@Param("id") Integer id);
}
