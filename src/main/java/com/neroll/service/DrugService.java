package com.neroll.service;

import com.neroll.mapper.DrugMapper;
import com.neroll.pojo.Drug;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService {
    @Autowired
    private DrugMapper drugMapper;
    public Result<PageInfo<Drug>> findDrugByPage(Integer pageNumber, Integer pageSize){
        if (pageNumber <= 0){
            return Result.error("页码错误");
        }
        if (pageSize <= 0){
            return Result.error("页大小错误");
        }
        int offset = (pageNumber - 1) * pageSize;
        int count = pageSize;
        List<Drug> drugs = drugMapper.getDrugByPage(offset,count);
        Integer total = drugMapper.getDrugCount();
        PageInfo<Drug> info = new PageInfo<>();
        info.setList(drugs);
        info.setTotal(total);
        return  Result.success("查询成功",info);
    }
}
