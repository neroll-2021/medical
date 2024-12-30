package com.neroll.mapper;

import com.neroll.pojo.City;
import com.neroll.pojo.CityVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CityMapper {

    List<CityVo> getCitiesByPage(@Param("offset") Integer offset,
                                 @Param("count") Integer count,
                                 @Param("keyword") String keyword);

    int getCityNum();

    int deleteCityById(@Param("id") Integer id);

    City getCityById(@Param("id") Integer id);

    int saveCity(City city);

    City getCityByNumber(@Param("number") Integer number);
}
