package com.example.panel.controller;

import com.example.panel.model.Reading;
import com.example.panel.model.WarningRange;
import com.example.panel.repository.ReadingRepository;
import com.example.panel.repository.WarningRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class ReadingController {
    @Autowired
    private ReadingRepository readingRepository;
    @Autowired
    private WarningRangeRepository warningRepository;

    @GetMapping("/dashboard-data")
    public String getDashboard(Model model) {
        Reading reading = readingRepository.findLatest();
        model.addAttribute("reading", reading);
        model.addAttribute("tempWarnings", warningRepository.findBySensorType("temperature"));
        model.addAttribute("fuelWarnings", warningRepository.findBySensorType("fuel"));
        model.addAttribute("speedWarnings", warningRepository.findBySensorType("speed"));
        return "dashboard";
    }

}