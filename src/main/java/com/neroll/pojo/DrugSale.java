package com.neroll.pojo;

public class DrugSale {
   private Long  id	;
    private  Long drugId;
    private Long saleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    @Override
    public String toString() {
        return "DrugSale{" +
                "id=" + id +
                ", drugId=" + drugId +
                ", saleId=" + saleId +
                '}';
    }
}
