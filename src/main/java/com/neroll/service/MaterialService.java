package com.neroll.service;

import com.neroll.mapper.MaterialMapper;
import com.neroll.pojo.Material;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MaterialService {
    @Autowired
    private MaterialMapper materialMapper;


    public Result<PageInfo<Material>> findMaterialByPage(Integer PageNumber, Integer PageSize, String Keyword) {
        if (PageNumber <= 0) {
            return Result.error("页码错误");
        }
        if (PageSize <= 0) {
            return Result.error("页大小错误");
        }
        int offset = (PageNumber - 1) * PageSize;
        int count = PageSize;
        String keyword = "%" + Keyword + "%";
        List<Material> materials = materialMapper.findMaterialByPage(offset, count, keyword);
        Integer total = materialMapper.getMaterialCount();
        PageInfo<Material> info = new PageInfo<>();
        info.setTotal(total);
        info.setList(materials);
        return Result.success("查询成功", info);
    }

    // 添加必备材料
    public Result<Material> addMaterial(Material material) {
        material.setCreateTime(LocalDateTime.now());
        material.setUpdateTime(LocalDateTime.now());
        int line = materialMapper.addMaterial(material);
        if (line == 0) {
            return Result.error("添加失败");
        }
        return Result.success("添加成功");
    }

    //修改必备材料信息
    public Result<Material> updateMaterial(Material material) {
        material.setUpdateTime(LocalDateTime.now());
        int line = materialMapper.updateMaterial((material));
        if (line == 0) {
            return Result.error("修改失败");
        }
        return Result.success("修改成功");
    }

}
