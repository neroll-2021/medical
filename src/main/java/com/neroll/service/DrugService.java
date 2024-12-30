package com.neroll.service;

import com.neroll.mapper.DrugMapper;
import com.neroll.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DrugService {
    @Autowired
    private DrugMapper drugMapper;

    public Result<PageInfo<DrugVo>> findDrugByPage(Integer pageNumber, Integer pageSize, String keyword) {
        if (pageNumber <= 0) {
            return Result.error("页码错误");
        }
        if (pageSize <= 0) {
            return Result.error("页大小错误");
        }
        int offset = (pageNumber - 1) * pageSize;
        int count = pageSize;
        String searchText = "%" + keyword + "%";
        List<DrugVo> drugs = drugMapper.getDrugByPage(offset, count, searchText);
        if (drugs == null)
            return Result.error("查询失败");

        for (DrugVo drug : drugs) {
            List<SaleVo> saleVoList = drugMapper.getSaleLocations(drug.getDrugId());
            if (saleVoList == null)
                return Result.error("查询失败");

            drug.setSaleLocations(saleVoList);
        }

        Integer total = drugMapper.getDrugNameLikeCount(searchText);
        PageInfo<DrugVo> info = new PageInfo<>();
        info.setList(drugs);
        info.setTotal(total);
        return Result.success("查询成功", info);
    }

    // 添加药品
    public Result<Drug> addDrug(Drug drug) {
        drug.setCreateTime(LocalDateTime.now());
        drug.setUpdateTime(LocalDateTime.now());
        int line = drugMapper.addDrug();
        if (line == 0) {
            return Result.error("添加失败");
        }
        return Result.success("添加成功");
    }
}
