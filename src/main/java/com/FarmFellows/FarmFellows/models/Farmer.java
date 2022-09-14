package com.FarmFellows.FarmFellows.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String userName;
    private String profilePicUrl;
    private int cashOnHand;
    private boolean admin;

    @OneToMany(mappedBy = "farmer")
    Set<FarmerCrop> crops;
    protected Farmer() {};
    public Farmer(String fullName, String userName) {
        this.fullName = fullName;
        this.cashOnHand = 50;
        this.profilePicUrl = "../img/default.png";
        this.userName = userName;
    }

    public Integer maxBuyQuantity(Crop c){
        Integer price = c.getSeedPrice();
        return this.cashOnHand/price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public int getCashOnHand() {
        return cashOnHand;
    }

    public void setCashOnHand(int cashOnHand) {
        this.cashOnHand = cashOnHand;
    }

    public Set<FarmerCrop> getCrops() {
        return crops;
    }

    public void setCrops(Set<FarmerCrop> crops) {
        this.crops = crops;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
