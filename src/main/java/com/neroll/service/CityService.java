package com.neroll.service;

import com.neroll.mapper.CityMapper;
import com.neroll.pojo.City;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityMapper mapper;

    public Result<PageInfo<City>> getCitiesByPage(Integer pageNum, Integer pageSize, String keyword) {
        if (pageNum <= 0)
            return Result.error("页码错误");
        if (pageSize <= 0)
            return Result.error("页大小错误");

        int offset = (pageNum - 1) * pageSize;
        int count = pageSize;

        keyword = "%" + keyword + "%";

        List<City> cities = mapper.getCitiesByPage(offset, count, keyword);
        if (cities == null)
            return Result.error("查询失败");

        int total = mapper.getCityNum();

//        CityInfo info = new CityInfo();
        PageInfo<City> info = new PageInfo<>();
        info.setTotal(total);
        info.setList(cities);
        return Result.success("查询成功", info);
    }

    public Result<City> deleteCityById(Integer id) {
        int line = mapper.deleteCityById(id);
        if (line == 0)
            return Result.error("城市不存在");
        return Result.success("删除成功");
    }
}
