package com.neroll.mapper;

import com.neroll.pojo.DoctorLevel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DoctorLevelMapper {

    List<DoctorLevel> getAllDoctorLevel();
}
