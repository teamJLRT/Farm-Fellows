package com.FarmFellows.FarmFellows.controllers;

import com.FarmFellows.FarmFellows.repositories.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CropController {

    @Autowired
    CropRepository cropRepository;

    @GetMapping("/crop")
    public String getCrop(){
        return "";
    }
}
