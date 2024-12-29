package com.neroll.service;

import com.neroll.mapper.SaleMapper;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.pojo.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleMapper saleMapper;

    public Result<PageInfo<Sale>> findSaleByPage(Integer pageNumber, Integer pageSize) {
        if (pageNumber <= 0) {
            return Result.error("页码错误");
        }
        if (pageSize <= 0) {
            return Result.error("页大小错误");
        }
        int offset = (pageNumber - 1) * pageSize;
        int count = pageSize;

        List<Sale> sales = saleMapper.getSaleByPage(offset, count);
        Integer total = saleMapper.getSaleCount();
        PageInfo<Sale> info = new PageInfo<>();
        info.setTotal(total);
        info.setList(sales);
        return Result.success("查询成功", info);


    }

    // 添加销售地点
    public Result<Sale> addSale(Sale sale) {
        sale.setCreateTime(LocalDateTime.now());
        sale.setUpdateTime(LocalDateTime.now());
        int line = saleMapper.saveSale(sale); //line 受影响的行数
        if (line == 0) {
            return Result.error("添加失败");
        }
        return Result.success("添加成功");
    }
}
