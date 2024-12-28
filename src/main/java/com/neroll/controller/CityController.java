package com.neroll.controller;

import com.neroll.pojo.City;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService service;

    @GetMapping
    public Result<PageInfo<City>> getCitiesByPage(@RequestParam("pn") Integer pageNum,
                                            @RequestParam("size") Integer pageSize,
                                            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        if (pageNum == null)
            return Result.error("页码不能为空");
        if (pageSize == null)
            return Result.error("页大小不能为空");
        return service.getCitiesByPage(pageNum, pageSize, keyword);
    }

    @DeleteMapping("/{id}")
    public Result<City> deleteCityById(@PathVariable Integer id) {
        if (id == null)
            return Result.error("城市 id 不能为空");

        return service.deleteCityById(id);
    }
}
