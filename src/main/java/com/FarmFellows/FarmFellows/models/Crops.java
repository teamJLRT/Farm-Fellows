package com.FarmFellows.FarmFellows.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Crops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cropName;
    private Integer cropGrowTime;
    private Integer seedPrice;
    private Integer cropSellPrice;

    public Crops() {
    }

    public Crops(String cropName, Integer seedPrice, Integer cropSellPrice, Integer cropGrowTime) {
        this.cropName = cropName;
        this.cropGrowTime = cropGrowTime;
        this.seedPrice = seedPrice;
        this.cropSellPrice = cropSellPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public Integer getCropGrowTime() {
        return cropGrowTime;
    }

    public void setCropGrowTime(Integer cropGrowTime) {
        this.cropGrowTime = cropGrowTime;
    }

    public Integer getSeedPrice() {
        return seedPrice;
    }

    public void setSeedPrice(Integer seedPrice) {
        this.seedPrice = seedPrice;
    }

    public Integer getCropSellPrice() {
        return cropSellPrice;
    }

    public void setCropSellPrice(Integer cropSellPrice) {
        this.cropSellPrice = cropSellPrice;
    }
}
