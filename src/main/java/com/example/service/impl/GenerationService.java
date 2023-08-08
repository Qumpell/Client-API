package com.example.service.impl;


import com.example.model.Generation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GenerationService {


    public int getYearFromDate(LocalDate date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
        return  Integer.parseInt(date.format(dateTimeFormatter));
    }
    public String checkGeneration(LocalDate dateTime){

        int birthYear = getYearFromDate(dateTime);

        if(birthYear < 1946){
            return "Boomer";
        }
        else if(birthYear >= 2010){
            return "Alpha";
        }
        else if(birthYear >= 1996){
            return "Z";
        }
        else if(birthYear >= 1977){
            return "'Millennial";
        }
        else if(birthYear >= 1965){
            return "X";
        }
        else{
            return "Baby Boomer";
        }
    }


    public String getGenerationOfDate(LocalDate date){
        Generation genAlpha = new Generation("Alpha",2010,getYearFromDate(LocalDate.now()));
        Generation genZ = new Generation("Z", 1996,2003);
        Generation millenials = new Generation("Millenials", 1977,1955);
        Generation genX = new Generation("X", 1956,1976);
        Generation genBabyBoomers = new Generation("Baby Boomers", 1946,1964);
        List<Generation> genList = List.of(genAlpha,genZ,millenials,genX,genBabyBoomers);

        int year = getYearFromDate(date);
        for (Generation gen: genList) {
            if(checkIfYearIsGeneration(gen,year)){
                return gen.getName();
            }
        }

        return "Boomers";
    }

    public boolean checkIfYearIsGeneration(Generation gen,int year) {
        return year <= gen.getEndYear() && year >= gen.getStartYear();
    }


}
