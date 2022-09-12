package com.FarmFellows.FarmFellows.repositories;

import com.FarmFellows.FarmFellows.models.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepository extends JpaRepository<Crop, Long> {
}
