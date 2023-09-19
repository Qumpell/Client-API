package com.example.unit;


import com.example.model.Generation;
import com.example.service.impl.GenerationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenerationServiceTest {
    private static GenerationService generationService;

    @BeforeAll
    public static void setUp() {
        generationService = new GenerationService();
    }

    @Test
    public void givenYearReturnIsA_Generation() {
        Generation genZ = new Generation("Z", 1996, 2003);
        boolean isGenZ = generationService.checkIfYearIsGeneration(genZ, 2001);

        assertTrue(isGenZ);
    }

    @Test
    public void Should_Give_A_Generation_When_Date_Is_Provided() {
        LocalDate date = LocalDate.of(1999, Month.AUGUST, 8);
        String actualGen = generationService.getGenerationOfDate(date);
        String expectedGen = "Z";
        assertEquals(expectedGen, actualGen);

    }

    @Test
    public void Should_Give_A_UnknownGeneration_When_Date_Out_Of_Range_Is_Provided() {
        LocalDate date = LocalDate.of(2400, Month.AUGUST, 8);
        String actualGen = generationService.getGenerationOfDate(date);
        String expectedGen = "unknown generation";
        assertEquals(expectedGen, actualGen);
    }
}
