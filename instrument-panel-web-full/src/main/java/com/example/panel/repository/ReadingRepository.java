package com.example.panel.repository;

import com.example.panel.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {

    @Query(value = "SELECT TOP 1 * FROM readings ORDER BY timestamp DESC", nativeQuery = true)
    Reading findLatest();

    @Query(value = "SELECT * FROM readings ORDER BY id ASC", nativeQuery = true)
    List<Reading> findAllByOrderByIdAsc();
}
