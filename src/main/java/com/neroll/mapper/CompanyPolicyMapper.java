package com.neroll.mapper;

import com.neroll.pojo.CompanyPolicy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyPolicyMapper {

    CompanyPolicy getPolicyById(@Param("id") Integer id);

    List<CompanyPolicy> getPolicyByPage(@Param("offset") Integer offset,
                                        @Param("count") Integer count,
                                        @Param("keyword") String keyword);

    int getPolicyCountNameLike(@Param("keyword") String keyword);

    int savePolicy(CompanyPolicy policy);

    int updatePolicy(@Param("id") Integer id, @Param("policy") CompanyPolicy policy);

    int deletePolicyById(@Param("id") Integer id);
}
