package com.FarmFellows.FarmFellows.controllers;

import com.FarmFellows.FarmFellows.models.Crop;
import com.FarmFellows.FarmFellows.models.Farmer;
import com.FarmFellows.FarmFellows.repositories.CropRepository;
import com.FarmFellows.FarmFellows.repositories.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Set;

@Controller
public class CropController {

    @Autowired
    CropRepository cropRepository;
    @Autowired
    FarmerRepository farmerRepository;

    @GetMapping("/crops")
    public String getCrop(@AuthenticationPrincipal OAuth2User principal, Model m){
        String userName = principal.getAttribute("name") + principal.getName();
        Farmer f = farmerRepository.findByuserName(userName);
        if (f != null){
            m.addAttribute("farmer", f);
        }
        List<Crop> crops = cropRepository.findAll();
        m.addAttribute("crops", crops);
        return "index";
    }

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

    @PutMapping("/crops")
    public RedirectView editCrop(@AuthenticationPrincipal OAuth2User principal, String editCropType, String editedCropName, Integer editedCropBuyPrice, Integer editedCropSellPrice, Integer editedCropGrowTime){
        String userName = principal.getAttribute("name") + principal.getName();
        Farmer f = farmerRepository.findByuserName(userName);
        if (f != null && f.isAdmin()){
            Crop c = cropRepository.findBycropName(editCropType);
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
}
