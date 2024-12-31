package com.neroll.pojo;

import java.util.List;

public class DrugDto extends Drug {
    private List<Long> saleId;

    public List<Long> getSaleId() {
        return saleId;
    }

    public void setSaleId(List<Long> saleId) {
        this.saleId = saleId;
    }

    @Override
    public String toString() {
        return "DrugDto{" +
                "saleId=" + saleId +
                '}';
    }
}
