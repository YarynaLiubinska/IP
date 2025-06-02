package com.example.panel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InstrumentPanelWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstrumentPanelWebApplication.class, args);
    }


}
