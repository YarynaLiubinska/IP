package com.example.panel.controller;

import com.example.panel.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;


    @GetMapping(value = "/api/dashboard/test", produces = "text/plain;charset=UTF-8")
    public String testDashboard() {
        dashboardService.updateDashboard();

        StringBuilder output = new StringBuilder();
        output.append(dashboardService.speedometer.render()).append("\n");
        output.append(dashboardService.speedWarning.render()).append("\n\n");
        output.append(dashboardService.rpmGauge.render()).append("\n");
        output.append(dashboardService.rpmWarning.render()).append("\n\n");
        output.append(dashboardService.fuelGauge.render()).append("\n");
        output.append(dashboardService.fuelWarning.render()).append("\n\n");
        output.append(dashboardService.temperatureGauge.render()).append("\n");
        output.append(dashboardService.temperatureWarning.render()).append("\n");

        return output.toString();
    }

}
