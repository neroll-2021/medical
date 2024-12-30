package com.neroll.controller;

import com.neroll.pojo.Material;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    private Result<Material> checkNonEmpty(Material material) {
        if (material == null) {
            return Result.error("必备材料信息不能为空");
        }
        if (!StringUtils.hasText(material.getTitle())) {
            return Result.error("类型不能为空");
        }
        if (!StringUtils.hasText(material.getMessage())) {
            return Result.error("材料信息不能为空");
        }
        return Result.success("添加成功");
    }

    @GetMapping
    public Result<PageInfo<Material>> findMaterialByPage(@RequestParam("pn") Integer pageNumber, @RequestParam("size") Integer pageSize, @RequestParam("keyword") String keyword) {
        if (pageNumber == null) {
            return Result.error("页码不能为空");
        }
        if (pageSize == null) {
            return Result.error("页大小不能为空");
        }
        return materialService.findMaterialByPage(pageNumber, pageSize, keyword);
    }

    @PostMapping
    public Result<Material> addMaterial(@RequestBody Material material) {
        Result<Material> materialResult = checkNonEmpty(material);
        if (materialResult.isError()) {
            return Result.error("添加失败");
        }
        return materialService.addMaterial(material);

    }

    @PutMapping("/{id}")
    public Result<Material> updateMaterial(@PathVariable Integer id, @RequestBody Material material) {
        Result<Material> materialResult = checkNonEmpty(material);
        if (materialResult.isError()) {
            return Result.error("修改失败");
        }
        material.setId(id);
        return materialService.updateMaterial(material);
    }

    @DeleteMapping("/{id}")
    public Result<Material> deleteMaterial(@PathVariable Integer id) {
        return materialService.deleteMaterial(id);
    }
}
