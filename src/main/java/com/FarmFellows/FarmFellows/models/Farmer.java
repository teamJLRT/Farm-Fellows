package com.FarmFellows.FarmFellows.models;

import org.hibernate.collection.internal.PersistentList;
import org.hibernate.collection.internal.PersistentSet;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Farmer implements Comparable<Farmer>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String userName;
    private String profilePicUrl;
    private String displayName;
    private int cashOnHand;
    private boolean admin;

    private boolean dailyBoost;

    @OneToMany(mappedBy = "farmer")
    Set<Planting> crops;

    @OneToMany(mappedBy = "farmer")
    Set<Friend> friends;

    @OneToMany(mappedBy = "friend")
    Set<Friend> friendOf;

    @OneToMany(mappedBy = "f")
    List<Comment> comments;

    protected Farmer() {};

    public Farmer(String fullName, String userName) {
        this.fullName = fullName;
        this.cashOnHand = 50;
        this.profilePicUrl = "../img/default.png";
        this.userName = userName;
        this.displayName = fullName;
        this.dailyBoost = true;
    }

    public boolean isFriend(Farmer f){
        for(Friend friendship : this.friends){
            if (friendship.friend.id == f.id){
                return true;
            }
        }
        return false;
    }

    public Integer maxBuyQuantity(Crop c){
        Integer price = c.getSeedPrice();
        return this.cashOnHand/price;
    }

    public Integer totalFarmValue() {
        Integer totalFarmValue = this.cashOnHand;
        if (this.crops == null){
            return 0;
        }

        for (Planting crop : this.crops) {
            totalFarmValue += crop.getQuantity() * crop.getCrop().getSeedPrice();
        }
        return totalFarmValue;
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

    public Set<Planting> getCrops() {
        return crops;
    }

    public void setCrops(Set<Planting> crops) {
        this.crops = crops;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Set<Friend> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
    }

    public Set<Friend> getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(Set<Friend> friendOf) {
        this.friendOf = friendOf;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isDailyBoost() {
        return dailyBoost;
    }

    public void setDailyBoost(boolean dailyBoost) {
        this.dailyBoost = dailyBoost;
    }

    @Override
    public int compareTo(Farmer farmer) {
        return farmer.totalFarmValue() - this.totalFarmValue();
    }


}
