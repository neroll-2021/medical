package com.neroll.mapper;

import com.neroll.pojo.DisplayedMedicalPolicy;
import com.neroll.pojo.MedicalPolicy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MedicalPolicyMapper {

    int getMedicalPolicyNum();

    List<DisplayedMedicalPolicy> getMedicalPolicyByPage(@Param("offset") Integer offset, @Param("count") Integer count);

    int saveMedicalPolicy(MedicalPolicy policy);

    int updateMedicalPolicy(MedicalPolicy policy);
}
