package com.example.panel.model;

public abstract class Instrument {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected double value; // значення, яке відображається

    public Instrument(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void moveTo(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public abstract String render(); // повертає строкове представлення приладу

    // Геттери і сеттери позиції/розміру
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
}
