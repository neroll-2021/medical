package com.neroll.mapper;

import com.neroll.pojo.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DoctorMapper {

    // 根据医师级别查找医师
    List<Doctor> findDoctorByLevelByPage(@Param("offset") Integer offset,
                                 @Param("count") Integer count,
                                 @Param("level") String level);

    int findDoctorCountByLevel(@Param("level") String level);
}
