package com.neroll.service;

import cn.dev33.satoken.stp.StpUtil;
import com.neroll.mapper.DrugMapper;
import com.neroll.mapper.DrugSaleMapper;
import com.neroll.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DrugService {
    @Autowired
    private DrugMapper drugMapper;
    @Autowired
    private DrugSaleMapper drugSaleMapper;

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
    public Result<Drug> addDrug(DrugDto drugDto) {
        drugDto.setCreateTime(LocalDateTime.now());
        drugDto.setUpdateTime(LocalDateTime.now());

        // 校验药品是否已存在
        List<Drug> d = drugMapper.getDrugByName(drugDto.getDrugName());
        if (d == null) {
            return Result.error("查询药品失败");
        }

        if (!d.isEmpty()) {
            return Result.error("该药品已存在");
        }
        // 根据登录的用户账号添加发布者
        Integer loginId = StpUtil.getLoginIdAsInt(); // 获取用户id
        String s = drugMapper.getDrugPublisher(loginId); // 获取用户姓名
        drugDto.setPublisher(s);

        int line = drugMapper.addDrug(drugDto);
        if (line == 0) {
            return Result.error("添加失败");
        }


        // 添加销售药店
        Long drugId = drugMapper.getLastInsertId(); // 获取最新插入数据

        // 遍历添加的销售药店的saleId列表，一个药品可以在多个药店售卖
        for (Long id : drugDto.getSaleId()) {
            DrugSale drugsale = new DrugSale();
            drugsale.setDrugId(drugId);
            drugsale.setSaleId(id);
            int row = drugSaleMapper.addDrugSale(drugsale);
            if (row == 0)
                return Result.error("添加销售药店失败");
        }
        return Result.success("添加成功");
    }
}
