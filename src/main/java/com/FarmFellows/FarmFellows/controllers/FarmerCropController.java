package com.FarmFellows.FarmFellows.controllers;

import com.FarmFellows.FarmFellows.models.Crop;
import com.FarmFellows.FarmFellows.models.Farmer;
import com.FarmFellows.FarmFellows.models.FarmerCrop;
import com.FarmFellows.FarmFellows.repositories.CropRepository;
import com.FarmFellows.FarmFellows.repositories.FarmerCropRepository;
import com.FarmFellows.FarmFellows.repositories.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;

@Controller
public class FarmerCropController {
    @Autowired
    CropRepository cropRepository;
    @Autowired
    FarmerRepository farmerRepository;
    @Autowired
    FarmerCropRepository farmerCropRepository;


    @PostMapping("/planting")
    public RedirectView buyCrops(Long farmerId, Long cropId, Integer quantity){
        Farmer f = farmerRepository.findById(farmerId).orElseThrow();
        Crop c = cropRepository.findById(cropId).orElseThrow();
        if (c != null && f != null){
            FarmerCrop planting = new FarmerCrop(f, c, LocalDateTime.now(), quantity);
            farmerCropRepository.save(planting);
        }

        return new RedirectView("/");
    }

}
