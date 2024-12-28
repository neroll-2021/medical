package com.neroll.pojo;

import java.util.List;

public class CompanyPolicyInfo {
    private Integer total;
    private List<CompanyPolicy> list;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<CompanyPolicy> getList() {
        return list;
    }

    public void setList(List<CompanyPolicy> list) {
        this.list = list;
    }
}
