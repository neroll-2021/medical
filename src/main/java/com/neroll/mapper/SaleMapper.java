package com.neroll.mapper;

import com.neroll.pojo.Sale;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SaleMapper {
    // 根据分页查询销售地点
    List<Sale> getSaleByPage(@Param("offset") Integer offset,
                             @Param("count") Integer count
    );

    Integer getSaleCount();

    Integer saveSale(Sale sale);
}
