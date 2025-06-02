package com.example.panel.repository;

import com.example.panel.model.WarningRange;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WarningRangeRepository extends JpaRepository<WarningRange, Long> {
    List<WarningRange> findBySensorType(String sensorType);
}