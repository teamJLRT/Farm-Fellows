package com.FarmFellows.FarmFellows.controllers;

import com.FarmFellows.FarmFellows.models.Crop;
import com.FarmFellows.FarmFellows.models.Farmer;
import com.FarmFellows.FarmFellows.models.Planting;
import com.FarmFellows.FarmFellows.repositories.CropRepository;
import com.FarmFellows.FarmFellows.repositories.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Controller
public class FarmerController {

    @Autowired
    FarmerRepository farmerRepository;
    @Autowired
    CropRepository cropRepository;

    @GetMapping("/")
    public String OAuthLogin(@AuthenticationPrincipal OAuth2User principal, Model m){

        if (principal != null) {
            String userName = principal.getAttribute("name") + principal.getName();
            Farmer f = farmerRepository.findByuserName(userName);
            if (f == null || f.getFullName() == null){
                f = new Farmer(principal.getAttribute("name"), userName);
                farmerRepository.save(f);
            }
            List<Crop> crops = cropRepository.findAll();
            Set<Planting> p = f.getCrops();
            m.addAttribute("plantings", p);
            m.addAttribute("cropList", crops);
            m.addAttribute("name", f.getFullName());
            m.addAttribute("farmer", f);
        }

        return "index";
    }

    @GetMapping("/all-farmers")
    public String getAllFarmers(@AuthenticationPrincipal OAuth2User principal, Model m){

        if (principal != null) {
            String userName = principal.getAttribute("name") + principal.getName();

            Farmer f = farmerRepository.findByuserName(userName);
            List<Farmer> allFarmers = farmerRepository.findAll();

            m.addAttribute("name", f.getFullName());
            m.addAttribute("farmerList", allFarmers);
        }
        return "all-farmers";
    }

    @GetMapping("/{id}")
    public String getUserInfo(@AuthenticationPrincipal OAuth2User principal, Model m, @PathVariable Long id){
        if (principal != null){
            String userName = principal.getAttribute("name") + principal.getName();
            Farmer f = farmerRepository.findByuserName(userName);
            m.addAttribute("farmer", f);
        }
        Farmer otherFarmer = farmerRepository.findById(id).orElseThrow();
        m.addAttribute("otherFarmer", otherFarmer);
        return "farmerpage";
    }
}
