package com.FarmFellows.FarmFellows.controllers;

import com.FarmFellows.FarmFellows.models.Crop;
import com.FarmFellows.FarmFellows.models.Farmer;
import com.FarmFellows.FarmFellows.models.Planting;
import com.FarmFellows.FarmFellows.repositories.CropRepository;
import com.FarmFellows.FarmFellows.repositories.PlantingRepository;
import com.FarmFellows.FarmFellows.repositories.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;

@Controller
public class PlantingController {
    @Autowired
    CropRepository cropRepository;
    @Autowired
    FarmerRepository farmerRepository;
    @Autowired
    PlantingRepository plantingRepository;


    @PostMapping("/planting")
    public RedirectView buyCrops(Long farmerId, Long cropId, Integer quantity){
        Farmer f = farmerRepository.findById(farmerId).orElseThrow();
        Crop c = cropRepository.findById(cropId).orElseThrow();
        if (c != null && f != null){
            Planting planting = new Planting(f, c, LocalDateTime.now(), quantity);
            Integer cost = c.getSeedPrice() * planting.getQuantity();
            f.setCashOnHand(f.getCashOnHand() - cost);
            farmerRepository.save(f);
            plantingRepository.save(planting);
        }
        return new RedirectView("/");
    }

    @PutMapping("/planting")
    public RedirectView sellCrops(Long plantingId, Integer quantity){
        Planting planting = plantingRepository.findById(plantingId).orElseThrow();
        Crop c = planting.getCrop();
        Farmer f = planting.getFarmer();
        Integer proceeds = quantity * c.getCropSellPrice();
        f.setCashOnHand(f.getCashOnHand() + proceeds);
        planting.setQuantity(planting.getQuantity() - quantity);
        farmerRepository.save(f);
        plantingRepository.save(planting);
        if (planting.getQuantity() == 0){
            plantingRepository.delete(planting);
        }
        return new RedirectView("/");
    }



}
