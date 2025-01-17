package com.neroll.pojo;

import java.time.LocalDateTime;

public class DisplayedMedicalPolicy {
    private Integer id;
    public String title;
    private String message;
    private CityVo cityVo;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CityVo getCity() {
        return cityVo;
    }

    public void setCity(CityVo cityVo) {
        this.cityVo = cityVo;
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

    @Override
    public String toString() {
        return "MedicalPolicy{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", city=" + cityVo +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
