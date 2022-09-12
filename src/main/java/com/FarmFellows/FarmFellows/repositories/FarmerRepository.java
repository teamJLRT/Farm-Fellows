package com.FarmFellows.FarmFellows.repositories;

import com.FarmFellows.FarmFellows.models.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    public Farmer findByemail(String email);
}
