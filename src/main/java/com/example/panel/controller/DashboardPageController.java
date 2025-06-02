package com.example.panel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardPageController {

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard"; // повертає dashboard.html із templates/
    }
}
