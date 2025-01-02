package com.neroll.controller;

import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.pojo.Sale;
import com.neroll.pojo.SaleVo;
import com.neroll.service.SaleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    private Result<Sale> checkNonEmpty(Sale sale) {
        if (sale == null) {
            return Result.error("销售地点数据不能为空");
        }
        if (!StringUtils.hasText(sale.getSaleName())) {
            return Result.error("销售名称不能为空");
        }
        if (!StringUtils.hasText(sale.getAddress())) {
            return Result.error("销售地址不能为空");
        }
        if (!StringUtils.hasText(sale.getSalePhone())) {
            return Result.error("销售电话不能为空");
        }

        return Result.success();
    }

    @GetMapping
    public Result<PageInfo<Sale>> findSaleByPage(@RequestParam("pn") Integer pageNumber, @RequestParam("size") Integer pageSize, @RequestParam("keyword") String keyword) {
        if (pageNumber == null) {
            return Result.error("页码不能为空");
        }
        if (pageSize == null) {
            return Result.error("页大小不能为空");
        }
        return saleService.findSaleByPage(pageNumber, pageSize, keyword);
    }

    @GetMapping("/all")
    public Result<List<SaleVo>> getAllSaleLocations() {
        return saleService.getAllSaleLocations();
    }

    @PostMapping
    public Result<Sale> addMaterial(@RequestBody Sale sale) {
        Result<Sale> checkResult = checkNonEmpty(sale);
        if (checkResult.isError())
            return checkResult;


        return saleService.addSale(sale);

    }

    @PutMapping("/{id}")
    public Result<Sale> updateSale(@PathVariable Long id, @RequestBody Sale sale) {
        Result<Sale> checkResult = checkNonEmpty(sale);
        if (checkResult.isError())
            return checkResult;
        sale.setSaleId(id);
        return saleService.updateSale(sale);
    }

    @DeleteMapping("/{id}")
    public Result<Sale> deleteSaleById(@PathVariable Long id) {
        return saleService.deleteSale(id);
    }

}
