package com.FarmFellows.FarmFellows.controllers;

import com.FarmFellows.FarmFellows.models.*;
import com.FarmFellows.FarmFellows.repositories.CommentRepository;
import com.FarmFellows.FarmFellows.repositories.CropRepository;
import com.FarmFellows.FarmFellows.repositories.FarmerRepository;
import com.FarmFellows.FarmFellows.repositories.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
public class FarmerController {

    @Autowired
    FarmerRepository farmerRepository;
    @Autowired
    CropRepository cropRepository;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    CommentRepository commentRepository;

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
            m.addAttribute("farmerId",f.getId());
        }

        return "index";
    }

    @GetMapping("/all-farmers")
    public String getAllFarmers(@AuthenticationPrincipal OAuth2User principal, Model m){

        if (principal != null) {
            String userName = principal.getAttribute("name") + principal.getName();

            Farmer f = farmerRepository.findByuserName(userName);
            List<Farmer> allFarmers = farmerRepository.findAll();
            Collections.sort(allFarmers);
            m.addAttribute("name", f.getFullName());
            m.addAttribute("farmerList", allFarmers);
            m.addAttribute("farmer",f);
        }
        return "all-farmers";
    }

    @GetMapping("/{id}")
    public String getUserInfo(@AuthenticationPrincipal OAuth2User principal, Model m, @PathVariable Long id){
        if (principal != null){
            String userName = principal.getAttribute("name") + principal.getName();
            Farmer f = farmerRepository.findByuserName(userName);
            m.addAttribute("name",userName);
            m.addAttribute("farmer", f);
            m.addAttribute("name", f.getFullName());
        }
        Farmer otherFarmer = farmerRepository.findById(id).orElseThrow();
        m.addAttribute("otherFarmer", otherFarmer);
        return "farmerpage";
    }

    @PostMapping("/{id}/addfriend")
    public RedirectView addFriend(@AuthenticationPrincipal OAuth2User principal, Long farmerId, @PathVariable Long id){
        if (principal != null) {
            Farmer farmer = farmerRepository.findById(farmerId).orElseThrow();
            Farmer otherFarmer = farmerRepository.findById(id).orElseThrow();
            LocalDateTime currentTime = LocalDateTime.now();
            Friend friendship = new Friend(farmer, otherFarmer, currentTime);
            friendRepository.save(friendship);
        }
        return new RedirectView("/" + id);
    }

    @PostMapping("/{id}/addcomment")
    public RedirectView addComment(@AuthenticationPrincipal OAuth2User principal, Long farmerId, String text, @PathVariable Long id){
        if(principal != null){
            Farmer receivingComment = farmerRepository.findById(id).orElseThrow();
            Farmer makingComment = farmerRepository.findById(farmerId).orElseThrow();
            Comment comment = new Comment(receivingComment, makingComment.getDisplayName(), text);
            commentRepository.save(comment);
        }
        return new RedirectView("/" + id);
    }

    @PutMapping("/{id}/displayname")
    public RedirectView changeDisplayName(@AuthenticationPrincipal OAuth2User principal, String newDisplayName, @PathVariable Long id){
        if (principal != null){
            String userName = principal.getAttribute("name") + principal.getName();
            Farmer f = farmerRepository.findByuserName(userName);
            f.setDisplayName(newDisplayName);
            farmerRepository.save(f);
        }
        return new RedirectView("/" + id);
    }

}
