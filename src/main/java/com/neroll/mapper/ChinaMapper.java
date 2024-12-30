package com.neroll.mapper;

import com.neroll.pojo.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChinaMapper {

    List<Region> getRegionsByName(@Param("name") String name);
}
