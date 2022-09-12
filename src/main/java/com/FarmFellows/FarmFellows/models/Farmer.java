package com.FarmFellows.FarmFellows.models;

import javax.persistence.*;
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

    private String email;

    @OneToMany(mappedBy = "farmer")
    Set<FarmerCrop> farmerCrops;
    protected Farmer() {};
    public Farmer(String fullName, String email) {
        this.fullName = fullName;
        this.cashOnHand = 50;
        this.profilePicUrl = "../img/default.png";
        this.email = email;
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

    public Set<FarmerCrop> getFarmerCrops() {
        return farmerCrops;
    }

    public void setFarmerCrops(Set<FarmerCrop> farmerCrops) {
        this.farmerCrops = farmerCrops;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
