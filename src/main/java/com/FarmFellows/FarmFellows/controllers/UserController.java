package com.FarmFellows.FarmFellows.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("/")
    public String OAuthLogin(@AuthenticationPrincipal OAuth2User principal, Model m){
        OAuth2User x = principal;
        if (principal != null){
            m.addAttribute("name", principal.getAttribute("name"));
            m.addAttribute("email", principal.getAttribute("email"));
        }
        return "index";
    }
}
