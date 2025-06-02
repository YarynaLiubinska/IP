package com.example.panel.model;

public class TemperatureGauge extends Instrument {

    public TemperatureGauge(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public String render() {
        return String.format("Температура: %.0f °C", value);
    }
}

