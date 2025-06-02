package com.example.panel.model;

public class Speedometer extends Instrument {

    public Speedometer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public String render() {
        return String.format("Швидкість: %.0f км/год", value);
    }
}
