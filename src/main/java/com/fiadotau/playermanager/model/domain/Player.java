package com.fiadotau.playermanager.model.domain;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/* TODO
 This class represents a Player entity and is designed to be compatible with Open CSV for data loading.
 In a domain-driven design ideally entities like Player would be immutable to ensure thread safety and avoid unintended side-effects.
 However, immutability is compromised in this class to facilitate the ease of data loading with Open CSV, which requires setters
 to populate object fields.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @CsvBindByName
    private String playerId;

    @CsvBindByName
    private Integer birthYear;

    @CsvBindByName
    private Integer birthMonth;

    @CsvBindByName
    private Integer birthDay;

    @CsvBindByName
    private String birthCountry;

    @CsvBindByName
    private String birthState;

    @CsvBindByName
    private String birthCity;

    @CsvBindByName
    private Integer deathYear;

    @CsvBindByName
    private Integer deathMonth;

    @CsvBindByName
    private Integer deathDay;

    @CsvBindByName
    private String deathCountry;

    @CsvBindByName
    private String deathState;

    @CsvBindByName
    private String deathCity;

    @CsvBindByName
    private String nameFirst;

    @CsvBindByName
    private String nameLast;

    @CsvBindByName
    private String nameGiven;

    @CsvBindByName
    private Integer weight;

    @CsvBindByName
    private Integer height;

    @CsvBindByName(column = "bats")
    private String batsData;

    @CsvBindByName(column = "throws")
    private String throwsData;

    @CsvBindByName
    @CsvDate(value = "yyyy-MM-dd")
    private LocalDate debut;

    @CsvBindByName
    @CsvDate(value = "yyyy-MM-dd")
    private LocalDate finalGame;

    @CsvBindByName
    private String retroID;

    @CsvBindByName
    private String bbrefID;
}
