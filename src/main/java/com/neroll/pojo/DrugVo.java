package com.neroll.pojo;

import java.util.List;

public class DrugVo extends Drug{
    private String saleLocations; // 保存药品对应的销售地点


    public String getSaleLocations() {
        return this.saleLocations;
    }

    public void setSaleLocations(String saleLocations) {
        this.saleLocations = saleLocations;
    }
}
