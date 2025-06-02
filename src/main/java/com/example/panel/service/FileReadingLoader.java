package com.example.panel.service;

import com.example.panel.model.Reading;
import com.example.panel.repository.ReadingRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class FileReadingLoader {

    @Autowired
    private ReadingRepository readingRepository;

    private List<String> lines;
    private int index = 0;

    @PostConstruct
    public void startReadingFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new ClassPathResource("data/readings.txt").getInputStream()));
            lines = reader.lines().collect(Collectors.toList());
            reader.close();

            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
                if (lines.isEmpty()) return;
                String line = lines.get(index);
                String[] parts = line.split(",");

                Reading reading = new Reading();
                reading.setSpeed(Integer.parseInt(parts[0].trim()));
                reading.setTemperature(Integer.parseInt(parts[1].trim()));
                reading.setFuel(Integer.parseInt(parts[2].trim()));
                reading.setRpm(Integer.parseInt(parts[3].trim()));
                readingRepository.save(reading);

                index = (index + 1) % lines.size(); // по колу

            }, 0, 2, TimeUnit.SECONDS); // кожні 2 секунди

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
