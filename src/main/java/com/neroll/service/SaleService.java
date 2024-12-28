package com.neroll.service;

import com.neroll.mapper.SaleMapper;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.pojo.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleMapper saleMapper;
    public Result<PageInfo<Sale>> findSaleByPage(Integer pageNumber, Integer pageSize) {
        if (pageNumber <= 0) {
            return  Result.error("页码错误");
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
}
