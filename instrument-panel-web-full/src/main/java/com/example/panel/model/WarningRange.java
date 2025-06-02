package com.example.panel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "warning_range") // ← Назва таблиці має точно відповідати тій, що в базі
public class WarningRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sensor_type")
    private String sensorType;

    @Column(name = "min_value")
    private int minValue;

    @Column(name = "max_value")
    private int maxValue;

    private String message;
    private String level;

    public WarningRange() {
        // Порожній конструктор обов’язковий для JPA
    }

    public WarningRange(String sensorType, int minValue, int maxValue, String message, String level) {
        this.sensorType = sensorType;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.message = message;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
