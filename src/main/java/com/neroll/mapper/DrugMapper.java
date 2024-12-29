package com.neroll.mapper;

import com.neroll.pojo.Drug;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DrugMapper {
    // 根据分页查询药品信息
    List<Drug> getDrugByPage(@Param("offset") Integer offset,@Param("count") Integer count);
    Integer getDrugCount();

}
