package com.example.panel.model;

import java.util.ArrayList;
import java.util.List;

public class WarningPanel {
    private String sensorType;
    private List<WarningRange> ranges = new ArrayList<>();
    private String currentMessage = "";
    private String currentLevel = "";

    public WarningPanel(String sensorType) {
        this.sensorType = sensorType;
    }

    public void setRanges(List<WarningRange> ranges) {
        this.ranges = ranges;
    }

    public void update(double value) {
        currentMessage = "Немає даних";
        currentLevel = "невідомо";

        for (WarningRange range : ranges) {
            if (value >= range.getMinValue() && value <= range.getMaxValue()) {
                currentMessage = range.getMessage();
                currentLevel = normalizeLevel(range.getLevel());
                return;
            }
        }
    }

    public String render() {
        return String.format("%s (%s)", currentMessage, sensorType);
    }

    private String normalizeLevel(String level) {
        if (level == null) return "невідомо";
        return level.trim().toLowerCase();
    }

    public String getCurrentMessage() {
        return currentMessage;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public String getSensorType() {
        return sensorType;
    }
}
