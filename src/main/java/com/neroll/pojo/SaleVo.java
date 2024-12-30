package com.neroll.pojo;

public class SaleVo {
    private Long saleId;
    private String saleName;

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    @Override
    public String toString() {
        return "SaleVo{" +
                "saleId=" + saleId +
                ", saleName='" + saleName + '\'' +
                '}';
    }
}
