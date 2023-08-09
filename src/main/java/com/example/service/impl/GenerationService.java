package com.example.service.impl;


import com.example.model.Generation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.example.model.GenerationKind.getGenerationKind;

@AllArgsConstructor
@Data
@Service
public class GenerationService {

    public String getGenerationOfDate(LocalDate date) {
        return getGenerationKind(date.getYear());
    }

    public boolean checkIfYearIsGeneration(Generation gen, int year) {
        return year <= gen.getEndYear() && year >= gen.getStartYear();
    }
}
