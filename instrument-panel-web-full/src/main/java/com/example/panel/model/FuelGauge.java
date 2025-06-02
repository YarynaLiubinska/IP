package com.example.panel.model;

public class FuelGauge extends Instrument {

    public FuelGauge(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public String render() {
        return String.format("Паливо: %.0f%%", value);
    }
}
