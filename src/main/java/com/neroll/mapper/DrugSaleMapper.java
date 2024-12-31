package com.neroll.mapper;

import com.neroll.pojo.DrugSale;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DrugSaleMapper {
    Integer addDrugSale(DrugSale drugSale);

    Integer deleteDrugSale(Long drugId);

}
