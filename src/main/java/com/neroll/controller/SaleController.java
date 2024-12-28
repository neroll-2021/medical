package com.neroll.controller;

import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.pojo.Sale;
import com.neroll.service.SaleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public Result<PageInfo<Sale>> findSaleByPage(@RequestParam("pn") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        if (pageNumber == null) {
            return Result.error("页码不能为空");
        }
        if (pageSize == null) {
            return Result.error("页大小不能为空");
        }
        return saleService.findSaleByPage(pageNumber, pageSize);
    }

}
