package com.example.lov_test;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Component
@RequiredArgsConstructor

public class LovDataInitializer implements CommandLineRunner {
    private final LovRepository lovRepository;
    private final LovService lovService;

    @Override
    public void run(String... args) throws Exception {
    try {
        initializeDefaultLovData();
    } catch (Exception e) {
        log.error("Failed to initialize default LOV data", e);
        throw new DataInitializationException("Failed to initialize default LOV data", e);
    }
    }

    private void initializeDefaultLovData() throws Exception {
        long count = lovRepository.count();
        if (count == 0) {
            log.info("Initializing default LOV data");
            List<ListOfValues> defaultLov = loadDefaultLovData();

            lovRepository.saveAll(defaultLov);
            lovService.clearLovCache();

            log.info("Successfully initialized {} LOV records", defaultLov.size());
        } else {
            log.info("LOV data already exists({} records) , skipping initialization", count);
        }
    }

    private List<ListOfValues> loadDefaultLovData() throws Exception {
        ClassPathResource resource = new ClassPathResource("lov.csv.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            return reader.lines()
                    .skip(1)
                    .map(this::parseCsvLine)
                    .collect(Collectors.toList());
        }
    }

    private ListOfValues parseCsvLine(String line) {
        try {
            String[] values = line.split(",");
            if (values.length != 5) {
                throw new IllegalArgumentException("Invalid CSV line: " + line);
            }
            return ListOfValues.builder()
                    .lovCode(values[0].trim())
                    .lovValue(values[1].trim())
                    .descriptionEn(values[2].trim())
                    .descriptionAr(values[3].trim())
                    .isActive(Boolean.parseBoolean(values[4].trim()))
                    .build();
        } catch (Exception e) {
            log.error("Failed to parse CSV line: {}", line, e);
            throw new DataInitializationException("Failed to parse CSV line: " + line, e);
        }
    }
        private static class DataInitializationException extends RuntimeException {
        public DataInitializationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}