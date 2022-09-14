package com.FarmFellows.FarmFellows.models;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
public class FarmerCrop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    Farmer farmer;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    Crop crop;

    LocalDateTime plantedAt;

    int quantity;

    protected FarmerCrop() {
    }

    public FarmerCrop(Farmer farmer, Crop crop, LocalDateTime plantedAt, int quantity) {
        this.farmer = farmer;
        this.crop = crop;
        this.plantedAt = plantedAt;
        this.quantity = quantity;
    }

    public Integer timeRemaining(){
        LocalDateTime currentTime = LocalDateTime.now();
        Integer timeBetween = Math.toIntExact(ChronoUnit.SECONDS.between(this.plantedAt, currentTime));
        return this.crop.getCropGrowTime() - timeBetween;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public LocalDateTime getPlantedAt() {
        return plantedAt;
    }

    public void setPlantedAt(LocalDateTime plantedAt) {
        this.plantedAt = plantedAt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

