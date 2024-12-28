package com.neroll.controller;

import com.neroll.pojo.CityInfo;
import com.neroll.pojo.Result;
import com.neroll.service.CityService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService service;

    @GetMapping
    public Result<CityInfo> getCitiesByPage(@RequestParam("pn") Integer pageNum,
                                            @RequestParam("size") Integer pageSize,
                                            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        if (pageNum == null)
            return Result.error("页码不能为空");
        if (pageSize == null)
            return Result.error("页大小不能为空");
        return service.getCitiesByPage(pageNum, pageSize, keyword);
    }
}
