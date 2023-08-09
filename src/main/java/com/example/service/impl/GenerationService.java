package com.example.service.impl;


import com.example.exception.GenerationNotFoundException;
import com.example.model.Generation;
import com.example.model.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.model.GenerationType.getGenerationType;

@AllArgsConstructor
@Data
public class GenerationService {


    public int getYearFromDate(LocalDate date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
        return Integer.parseInt(date.format(dateTimeFormatter));
    }

    public String checkGeneration(LocalDate dateTime) {

        int birthYear = getYearFromDate(dateTime);

        if (birthYear < 1946) {
            return "Boomer";
        } else if (birthYear >= 2010) {
            return "Alpha";
        } else if (birthYear >= 1996) {
            return "Z";
        } else if (birthYear >= 1977) {
            return "'Millennial";
        } else if (birthYear >= 1965) return "X";
        return "Baby Boomer";
    }


    public String getGenerationOfDate(LocalDate date) {
        return getGenerationType(getYearFromDate(date));
    }

    public boolean checkIfYearIsGeneration(Generation gen, int year) {
        return year <= gen.getEndYear() && year >= gen.getStartYear();
    }
}
