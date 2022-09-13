package com.FarmFellows.FarmFellows.models;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
public class FarmerCrop {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;
    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    private int quantity;

    private LocalDateTime planted;

    protected FarmerCrop() {};

    public FarmerCrop(Farmer farmer, Crop crop, int quantity, LocalDateTime planted) {
        this.farmer = farmer;
        this.crop = crop;
        this.quantity = quantity;
        this.planted = planted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDateTime planted) {
        this.planted = planted;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public Integer secondsLeft(){
        LocalDateTime currentTime = LocalDateTime.now();
        Integer secondsSincePlanted = Math.toIntExact(ChronoUnit.SECONDS.between(this.planted, currentTime));
        Integer secondsRemaining = this.crop.getCropGrowTime() - secondsSincePlanted;
        return secondsRemaining;
    }

    public boolean isReady(){
        return secondsLeft() <= 0;
    }


}
