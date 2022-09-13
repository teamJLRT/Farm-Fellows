package com.FarmFellows.FarmFellows.controllers;

import com.FarmFellows.FarmFellows.models.Farmer;
import com.FarmFellows.FarmFellows.repositories.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @Autowired
    FarmerRepository farmerRepository;

    @GetMapping("/")
    public String OAuthLogin(@AuthenticationPrincipal OAuth2User principal, Model m){

        if (principal != null) {
            String userName = principal.getAttribute("name") + principal.getName();
            Farmer f = farmerRepository.findByuserName(userName);
            if (f == null || f.getFullName() == null){
                f = new Farmer(principal.getAttribute("name"), userName);
                farmerRepository.save(f);
            }
            m.addAttribute("name", f.getFullName());
            m.addAttribute("farmer", f);
        }

        return "index";
    }
}
