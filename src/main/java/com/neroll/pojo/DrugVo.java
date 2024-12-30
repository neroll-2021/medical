package com.neroll.pojo;

import java.util.List;

public class DrugVo extends Drug {

    private List<SaleVo> saleLocations; // 保存药品对应的销售地点


    public List<SaleVo> getSaleLocations() {
        return this.saleLocations;
    }

    public void setSaleLocations(List<SaleVo> saleLocations) {
        this.saleLocations = saleLocations;
    }
}
