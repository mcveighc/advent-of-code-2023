package com.oc.aoc;

import java.util.*;


import org.javatuples.Pair;

public class Trebuchet {

    private static List<Pair<String, String>> numberKeywordPairs = List.of(
        new Pair<String,String>("one", "1e"),
        new Pair<String,String>("two", "2o"),
        new Pair<String,String>("three", "3e"),
        new Pair<String,String>("four", "4r"),
        new Pair<String,String>("five", "5e"),
        new Pair<String,String>("six", "6x"),
        new Pair<String,String>("seven", "7n"),
        new Pair<String,String>("eight", "8t"),
        new Pair<String,String>("nine", "9e")
    );

    public static int calibrateDocument(String[] calibrations) {
        int result = 0;
        for(String calibrationReading : calibrations) {
            int value = getCalibrationValue(calibrationReading);
            result = result + value;
        }
        return result;
    }

    public static int getCalibrationValue(String reading) {
        String normalisedReading = normaliseCalibrationReading(reading);
        String digits = normalisedReading.replaceAll("[^\\d.]", "");

        return digits.isEmpty() ? 0 : constructCalibrationValue(digits);
    }

    private static int constructCalibrationValue(String digits) {
        char first = digits.charAt(0);
        char last = digits.charAt(digits.length() - 1);

        String calibrationValue = String.valueOf(first) + String.valueOf(last);
        return Integer.parseInt(calibrationValue);
    }

    public static String normaliseCalibrationReading(String string) {
        String normalisedReading = "";
        
        for(int i =0; i<string.length(); i++) {
            char letter = string.charAt(i);
            normalisedReading = normalisedReading + String.valueOf(letter);

            for(int j=0; j<numberKeywordPairs.size(); j++) {
                String numberKeyword = numberKeywordPairs.get(j).getValue0();
                String numberKeywordValue = numberKeywordPairs.get(j).getValue1();

                normalisedReading = normalisedReading.replace(numberKeyword, numberKeywordValue);
            }
        }

        return normalisedReading;
    }
}