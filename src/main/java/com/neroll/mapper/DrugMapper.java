package com.neroll.mapper;

import com.neroll.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DrugMapper {
    // 根据分页查询药品信息
    List<DrugVo> getDrugByPage(@Param("offset") Integer offset,
                               @Param("count") Integer count,
                               @Param("keyword") String keyword);

    List<SaleVo> getSaleLocations(Long drugId);

    Integer getDrugCount();

    Integer getDrugNameLikeCount(@Param("keyword") String keyword);

    Integer addDrug(Drug drug);

    Long getLastInsertId();

    List<Drug> getDrugByName(String drugName);

    String getDrugPublisher(Integer userId);

}
