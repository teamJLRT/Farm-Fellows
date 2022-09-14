package com.FarmFellows.FarmFellows.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    Farmer farmer;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    Farmer friend;

    LocalDateTime friendedAt;

    protected Friend() {
    }

    public Friend(Farmer farmer, Farmer friend, LocalDateTime friendedAt) {
        this.farmer = farmer;
        this.friend = friend;
        this.friendedAt = friendedAt;
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

    public Farmer getFriend() {
        return friend;
    }

    public void setFriend(Farmer friend) {
        this.friend = friend;
    }

    public LocalDateTime getFriendedAt() {
        return friendedAt;
    }

    public void setFriendedAt(LocalDateTime friendedAt) {
        this.friendedAt = friendedAt;
    }
}
