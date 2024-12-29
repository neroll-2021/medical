package com.neroll.service;

import com.neroll.mapper.MaterialMapper;
import com.neroll.pojo.Material;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    @Autowired
    private MaterialMapper materialMapper;

    public Result<PageInfo<Material>> findMaterialByPage(Integer PageNumber, Integer PageSize) {
        if (PageNumber <= 0) {
            return Result.error("页码错误");
        }
        if (PageSize <= 0) {
            return Result.error("页大小错误");
        }
        int offset = (PageNumber - 1) * PageSize;
        int count = PageSize;
        List<Material> materials = materialMapper.findMaterialByPage(offset, count);
        Integer total = materialMapper.getMaterialCount();
        PageInfo<Material> info = new PageInfo<>();
        info.setTotal(total);
        info.setList(materials);
        return Result.success("查询成功", info);
    }
}
