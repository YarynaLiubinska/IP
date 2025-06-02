package com.example.panel.service;

import com.example.panel.model.*;
import com.example.panel.repository.ReadingRepository;
import com.example.panel.repository.WarningRangeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ReadingRepository readingRepository;

    @Autowired
    private WarningRangeRepository warningRangeRepository;

    public Speedometer speedometer = new Speedometer(0, 0, 200, 200);
    public RPMGauge rpmGauge = new RPMGauge(220, 0, 200, 200);
    public FuelGauge fuelGauge = new FuelGauge(0, 220, 200, 200);
    public TemperatureGauge temperatureGauge = new TemperatureGauge(220, 220, 200, 200);

    public WarningPanel speedWarning = new WarningPanel("speed");
    public WarningPanel rpmWarning = new WarningPanel("rpm");
    public WarningPanel fuelWarning = new WarningPanel("fuel");
    public WarningPanel temperatureWarning = new WarningPanel("temperature");

    private List<Reading> allReadings;
    private int currentIndex = 0;

    @PostConstruct
    public void init() {
        try {
            loadReadingsFromFile();
        } catch (IOException e) {
            System.err.println("❌ Помилка зчитування readings.txt: " + e.getMessage());
        }

        allReadings = readingRepository.findAllByOrderByIdAsc();
        System.out.println("✅ Завантажено записів у памʼять: " + allReadings.size());
    }

    public void updateDashboard() {
        if (allReadings == null || allReadings.isEmpty()) return;

        Reading reading = allReadings.get(currentIndex);
        currentIndex = (currentIndex + 1) % allReadings.size();

        speedometer.setValue(reading.getSpeed());
        rpmGauge.setValue(reading.getRpm());
        fuelGauge.setValue(reading.getFuel());
        temperatureGauge.setValue(reading.getTemperature());

        updateWarning(speedWarning, "speed", reading.getSpeed());
        updateWarning(rpmWarning, "rpm", reading.getRpm());
        updateWarning(fuelWarning, "fuel", reading.getFuel());
        updateWarning(temperatureWarning, "temperature", reading.getTemperature());
    }

    private void updateWarning(WarningPanel panel, String type, int value) {
        List<WarningRange> ranges = warningRangeRepository.findBySensorType(type);
        panel.setRanges(ranges);
        panel.update(value);
    }

    private void loadReadingsFromFile() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("readings.txt");
        if (inputStream == null) {
            System.err.println("❌ Файл readings.txt не знайдено в resources!");
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        readingRepository.deleteAll();
        System.out.println("🧹 Очищено таблицю readings");

        String line;
        int count = 0;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",");
            if (parts.length != 4) continue;

            try {
                int speed = Integer.parseInt(parts[0].trim());
                int temperature = Integer.parseInt(parts[1].trim());
                int fuel = Integer.parseInt(parts[2].trim());
                int rpm = Integer.parseInt(parts[3].trim());

                Reading reading = new Reading();
                reading.setSpeed(speed);
                reading.setTemperature(temperature);
                reading.setFuel(fuel);
                reading.setRpm(rpm);
                readingRepository.save(reading);
                count++;
            } catch (NumberFormatException e) {
                System.err.println("⚠ Пропущено рядок з помилкою: " + line);
            }
        }

        System.out.println("✅ Імпортовано " + count + " записів з readings.txt");
    }
}
