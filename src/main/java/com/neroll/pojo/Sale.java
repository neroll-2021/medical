package com.neroll.pojo;

import java.time.LocalDateTime;

public class Sale {
    private  Long saleId;
    private  String saleName;
    private String salePhone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Double lng;
    private Double lat;
    private String address;


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

    public String getSalePhone() {
        return salePhone;
    }

    public void setSalePhone(String salePhone) {
        this.salePhone = salePhone;
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

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "sale{" +
                "saleId=" + saleId +
                ", saleName='" + saleName + '\'' +
                ", salePhone='" + salePhone + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", lng=" + lng +
                ", lat=" + lat +
                ", address='" + address + '\'' +
                '}';
    }
}
