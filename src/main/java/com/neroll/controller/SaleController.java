package com.neroll.controller;

import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.pojo.Sale;
import com.neroll.service.SaleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Result<Sale> addMaterial(@RequestBody Sale sale) {
        if (sale == null) {
            return Result.error("销售地点数据不能为空");
        }
        // 非空校验
        if (!StringUtils.hasText(sale.getSaleName())) {
            return Result.error("销售名称不能为空");
        }
        if (!StringUtils.hasText(sale.getAddress())) {
            return Result.error("销售地址不能为空");
        }
        if (!StringUtils.hasText(sale.getSalePhone())) {
            return Result.error("销售电话不能为空");
        }
        if (sale.getLng() == null) {
            return Result.error("lng不能为空");
        }
        if (sale.getLat() == null) {
            return Result.error("Lat不能为空");
        }
        return saleService.addSale(sale);

    }

}
