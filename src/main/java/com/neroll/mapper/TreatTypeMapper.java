package com.neroll.mapper;

import com.neroll.pojo.TreatType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TreatTypeMapper {

    List<TreatType> getAllTreatType();
}
