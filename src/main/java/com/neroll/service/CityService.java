package com.neroll.service;

import com.neroll.mapper.ChinaMapper;
import com.neroll.mapper.CityMapper;
import com.neroll.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private ChinaMapper chinaMapper;

    public Result<PageInfo<CityVo>> getCitiesByPage(Integer pageNum, Integer pageSize, String keyword) {
        if (pageNum <= 0)
            return Result.error("页码错误");
        if (pageSize <= 0)
            return Result.error("页大小错误");

        int offset = (pageNum - 1) * pageSize;
        int count = pageSize;

        keyword = "%" + keyword + "%";

        List<CityVo> cities = cityMapper.getCitiesByPage(offset, count, keyword);
        if (cities == null)
            return Result.error("查询失败");

        int total = cityMapper.getCityNum();

//        CityInfo info = new CityInfo();
        PageInfo<CityVo> info = new PageInfo<>();
        info.setTotal(total);
        info.setList(cities);
        return Result.success("查询成功", info);
    }

    public Result<CityVo> deleteCityById(Integer id) {
        int line = cityMapper.deleteCityById(id);
        if (line == 0)
            return Result.error("城市不存在");
        return Result.success("删除成功");
    }

    public Result<City> addCity(String name) {
        List<Region> regions = chinaMapper.getRegionsByName(name);
        if (regions == null)
            return Result.error("查询城市信息失败");
        if (regions.isEmpty())
            return Result.error("城市不存在");
        if (regions.size() != 1)
            return Result.error("城市重名（这怎么可能？）");

        Region region = regions.get(0);
        Integer id = region.getId();

        City city = new City();
        city.setCityNumber(id);
        city.setCreateTime(LocalDateTime.now());
        city.setUpdateTime(LocalDateTime.now());

        int line = cityMapper.saveCity(city);
        if (line == 0)
            return Result.error("添加失败");
        return Result.success("添加成功");
    }
}
