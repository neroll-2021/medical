package com.neroll.service;

import com.neroll.mapper.DrugMapper;
import com.neroll.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DrugService {
    @Autowired
    private DrugMapper drugMapper;

    public Result<PageInfo<DrugVo>> findDrugByPage(Integer pageNumber, Integer pageSize) {
        if (pageNumber <= 0) {
            return Result.error("页码错误");
        }
        if (pageSize <= 0) {
            return Result.error("页大小错误");
        }
        int offset = (pageNumber - 1) * pageSize;
        int count = pageSize;
        List<DrugVo> drugs = drugMapper.getDrugByPage(offset, count);
        if (drugs != null && drugs.size() > 0) {
            for (DrugVo drug : drugs) {
                List<Sale> saleList = drugMapper.getSaleLocation(drug.getDrugId());
                if (saleList != null && saleList.size() > 0) {
                    String temp = "";
                    for (int i = 0; i < saleList.size(); i++) {
                        if (i < saleList.size() - 1) {
                            temp += saleList.get(i).getSaleName() + ",";

                        } else {
                            temp += saleList.get(i).getSaleName();

                        }
                    }
                    drug.setSaleLocations(temp);


                }
            }
        }
        Integer total = drugMapper.getDrugCount();
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
