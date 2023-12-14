package com.fiadotau.playermanager.model.repository;

import com.fiadotau.playermanager.model.domain.Player;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class RepositoryConfig {

    private static final String CSV_FILE_PATH = "player.csv";

    /*
     This configuration currently sets up a bean to hold player data in-memory, which is read from a CSV file.

     In a scenario with more time a more robust and sustainable approach would be to:
     - Utilize a relational database for data storage. This would provide better support for large datasets, more complex queries, and transaction management.
     - Leverage Spring Data JPA Repositories for data access. This would offer a more powerful and flexible way to interact with the database, including automatic query generation and easier data manipulation.
     - Implement Liquibase or a similar database version control tool for managing database schema changes and migrations. This ensures a systematic and version-controlled way of handling database schema evolution.
     */
    @Bean
    public Map<String, Player> playerDatasource() {
        try (
                InputStream inputStream = new ClassPathResource(CSV_FILE_PATH).getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
        ) {
            CsvToBean<Player> csvToBean = new CsvToBeanBuilder<Player>(reader)
                    .withType(Player.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.stream()
                    .collect(Collectors.toMap(Player::getPlayerId, player -> player));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load player data from CSV file", e);
        }
    }
}
