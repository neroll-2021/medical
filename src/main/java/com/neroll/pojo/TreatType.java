package com.neroll.pojo;

public class TreatType {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TreatTypeVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
