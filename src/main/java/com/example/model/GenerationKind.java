package com.example.model;

import java.time.LocalDateTime;
import java.util.Arrays;

public enum GenerationKind {
    GENERATION_ALPHA(2010, LocalDateTime.now().getYear(), "Alpha"),
    GENERATION_Z(1996, 2003, "Z"),
    GENERATION_MILLENIALS(1977, 1995, "Millenials"),
    GENERATION_X(1956, 1976, "X"),
    GENERATION_BABY_BOOMERS(1946, 1955, "Baby Boomers");


    private final int startYear;
    private final int endYear;
    private final String name;

    GenerationKind(int startYear, int endYear, String name) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.name = name;
    }

    public static String getGenerationKind(int birthYear){
        var genType = Arrays.stream(GenerationKind.values())
                .filter(type -> type.startYear<=birthYear && type.endYear>=birthYear)
                .findFirst();
        return genType.map(gen -> gen.name).orElse("unknown generation");
    }

}
