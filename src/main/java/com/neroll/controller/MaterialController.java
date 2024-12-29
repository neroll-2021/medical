package com.neroll.controller;

import com.neroll.pojo.Material;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping
    public Result<PageInfo<Material>> findMaterialByPage(@RequestParam("pn") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        if (pageNumber == null) {
            return Result.error("页码不能为空");
        }
        if (pageSize == null) {
            return Result.error("页大小不能为空");
        }
        return materialService.findMaterialByPage(pageNumber, pageSize);
    }
}
