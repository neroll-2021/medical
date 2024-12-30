package com.neroll.controller;

import com.neroll.pojo.Drug;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/drug")
public class DrugController {
    @Autowired
    private DrugService drugService;
    @GetMapping
    public Result<PageInfo<Drug>> findDrugByPage (@RequestParam("pn") Integer pageNumber, @RequestParam("size") Integer pageSize){
        if(pageNumber == null) {
            return Result.error("页码不能为空");
        }
        if(pageSize == null) {
            return Result.error("页大小不能为空");

        }
        return drugService.findDrugByPage(pageNumber, pageSize);
    }


}
