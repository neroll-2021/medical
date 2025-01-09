package com.neroll.pojo;



public class BarDataVO {//统计医生数据，横坐标医生级别，纵坐标医生人数，数组存储
    private String lev;// 级别
    private Integer count;// 人数

    public String getLev() {
        return lev;
    }

    public void setLev(String lev) {
        this.lev = lev;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
