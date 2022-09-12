package com.FarmFellows.FarmFellows.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cropName;
    private Integer cropGrowTime;
    private Integer seedPrice;
    private Integer cropSellPrice;

    @OneToMany(mappedBy = "crops")
    private Set<Farmer> farmers;
    public Crop() {
    }

    public Crop(String cropName, Integer seedPrice, Integer cropSellPrice, Integer cropGrowTime) {
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

    public Set<Farmer> getFarmers() {
        return farmers;
    }

    public void setFarmers(Set<Farmer> farmers) {
        this.farmers = farmers;
    }
}
