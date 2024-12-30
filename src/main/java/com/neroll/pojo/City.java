package com.neroll.pojo;

import java.time.LocalDateTime;

public class City {
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer cityNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCityNumber() {
        return cityNumber;
    }

    public void setCityNumber(Integer cityNumber) {
        this.cityNumber = cityNumber;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", cityNumber=" + cityNumber +
                '}';
    }
}
