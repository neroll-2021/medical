package com.neroll.controller;

import com.neroll.pojo.*;
import com.neroll.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drug")
public class DrugController {
    @Autowired
    private DrugService drugService;

    // 检查添加药品信息是否为空
    private Result<Drug> checkNonEmpty(Drug drug) {
        if (!StringUtils.hasText(drug.getDrugName())) {
            return Result.error("药品名称不能为空");
        }
        if (!StringUtils.hasText(drug.getDrugInfo())) {
            return Result.error("药品信息不能为空");
        }
        if (!StringUtils.hasText(drug.getDrugEffect())) {
            return Result.error("药品功能不能为空");
        }
        if (!StringUtils.hasText(drug.getDrugImg())) {
            return Result.error("药品图片不能为空");
        }
        return Result.success("添加成功");
    }

    @GetMapping
    public Result<PageInfo<DrugVo>> findDrugByPage(@RequestParam("pn") Integer pageNumber,
                                                   @RequestParam("size") Integer pageSize,
                                                   @RequestParam("keyword") String keyword) {
        if (pageNumber == null) {
            return Result.error("页码不能为空");
        }
        if (pageSize == null) {
            return Result.error("页大小不能为空");

        }
        return drugService.findDrugByPage(pageNumber, pageSize, keyword);
    }

    @PostMapping
    public Result<Drug> addDrug(@RequestBody DrugDto drugDto) {
        Result<Drug> checkResult = checkNonEmpty(drugDto);
        if (checkResult.isError())
            return checkResult;
        if (drugDto.getSaleId() == null)
            return Result.error("销售地点不能为空");

        return drugService.addDrug(drugDto);
    }

    @DeleteMapping("/{id}")
    public Result<Drug> deleteDrugById(@PathVariable Long id) {
        return drugService.deleteDrugById(id);
    }


}
