package com.FarmFellows.FarmFellows.repositories;

import com.FarmFellows.FarmFellows.models.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
