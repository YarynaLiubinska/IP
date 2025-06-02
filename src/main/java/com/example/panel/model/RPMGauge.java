package com.example.panel.model;

public class RPMGauge extends Instrument {

    public RPMGauge(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public String render() {
        return String.format("Оберти: %.0f об/хв", value);
    }
}
