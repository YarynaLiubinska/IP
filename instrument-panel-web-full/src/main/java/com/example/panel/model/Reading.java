package com.example.panel.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "readings")
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int speed;
    private int temperature;
    private int fuel;
    private int rpm;

    private LocalDateTime timestamp = LocalDateTime.now();

    // --- Геттери і Сеттери ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public int getTemperature() { return temperature; }
    public void setTemperature(int temperature) { this.temperature = temperature; }

    public int getFuel() { return fuel; }
    public void setFuel(int fuel) { this.fuel = fuel; }

    public int getRpm() { return rpm; }
    public void setRpm(int rpm) { this.rpm = rpm; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
