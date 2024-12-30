package com.neroll.mapper;

import com.neroll.pojo.Drug;
import com.neroll.pojo.DrugVo;
import com.neroll.pojo.Sale;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DrugMapper {
    // 根据分页查询药品信息
    List<DrugVo> getDrugByPage(@Param("offset") Integer offset, @Param("count") Integer count);

    List<Sale> getSaleLocation(Long drugId);

    Integer getDrugCount();

    Integer addDrug();

}
