package com.example;

import com.example.exception.GenerationNotFoundException;
import com.example.model.Generation;
import com.example.service.impl.GenerationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;


public class GenerationServiceTest {
    @Test
    public void givenYearReturnIsA_Generation(){
        GenerationService generationService = new GenerationService();
        Generation genZ = new Generation("Z", 1996,2003);
        int year = 2001;
        boolean isGenZ = generationService.checkIfYearIsGeneration(genZ,2001);

        assertTrue(isGenZ);
    }

    @Test
    public void Should_Give_A_Generation_When_Date_Is_Provided(){
        GenerationService generationService = new GenerationService();

        LocalDate date = LocalDate.of(1999, Month.AUGUST,8);
        String actualGen =  generationService.getGenerationOfDate(date);
        String expectedGen = "Z";
        assertEquals(expectedGen,actualGen);

    }


    @Test
    public void Should_Give_A_Generation_When_w3322Date_Is_Provided(){
        GenerationService generationService = new GenerationService();

        LocalDate date = LocalDate.of(2400, Month.AUGUST,8);
        String actualGen =  generationService.getGenerationOfDate(date);
        String expectedGen = "unknown generation";
        assertEquals(expectedGen,actualGen);

    }
}
