package com.FarmFellows.FarmFellows.controllers;

import com.FarmFellows.FarmFellows.models.Crop;
import com.FarmFellows.FarmFellows.models.Farmer;
import com.FarmFellows.FarmFellows.models.Planting;
import com.FarmFellows.FarmFellows.repositories.CropRepository;
import com.FarmFellows.FarmFellows.repositories.FarmerRepository;
import com.FarmFellows.FarmFellows.repositories.PlantingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Controller
public class CropController {

    @Autowired
    CropRepository cropRepository;
    @Autowired
    FarmerRepository farmerRepository;

    @Autowired
    PlantingRepository plantingRepository;


    @PostMapping("/crops")
    public RedirectView addCrop(@AuthenticationPrincipal OAuth2User principal, String newCropName, Integer newCropBuyPrice, Integer newCropSellPrice, Integer newCropGrowTime){
        String userName = principal.getAttribute("name") + principal.getName();
        Farmer f = farmerRepository.findByuserName(userName);
        if (f != null && f.isAdmin()){
            Crop newCrop = new Crop(newCropName, newCropBuyPrice, newCropSellPrice, newCropGrowTime);
            cropRepository.save(newCrop);
        }
        return new RedirectView("/");
    }

    @PostMapping("/useboost")
    public RedirectView useBoost(Long plantingId){
        Planting p = plantingRepository.findById(plantingId).orElseThrow();
        if (p.getFarmer().isDailyBoost()){
            LocalDateTime newPlantingTime = p.getPlantedAt().minusSeconds(p.getCrop().getCropGrowTime());
            p.setPlantedAt(newPlantingTime);
            plantingRepository.save(p);
            p.getFarmer().setDailyBoost(false);
            farmerRepository.save(p.getFarmer());
        }
        return new RedirectView("/");
    }

    @PutMapping("/crops")
    public RedirectView editCrop(@AuthenticationPrincipal OAuth2User principal, Long editCropType, String editedCropName, Integer editedCropBuyPrice, Integer editedCropSellPrice, Integer editedCropGrowTime){
        String userName = principal.getAttribute("name") + principal.getName();
        Farmer f = farmerRepository.findByuserName(userName);
        if (f != null && f.isAdmin()){
            Crop c = cropRepository.findById(editCropType).orElseThrow();
            if (c != null){
                c.setCropName(editedCropName);
                c.setSeedPrice(editedCropBuyPrice);
                c.setCropSellPrice(editedCropSellPrice);
                c.setCropGrowTime(editedCropGrowTime);
                cropRepository.save(c);
            }
        }
        return new RedirectView("/");
    }

    @DeleteMapping("/crops")
    public RedirectView deleteCrop(@AuthenticationPrincipal OAuth2User principal, Long deleteCropType){
        String userName = principal.getAttribute("name") + principal.getName();
        Farmer f = farmerRepository.findByuserName(userName);
        if (f != null && f.isAdmin()){
            Crop c = cropRepository.findById(deleteCropType).orElseThrow();
            cropRepository.delete(c);
        }
        return new RedirectView("/");
    }
}
