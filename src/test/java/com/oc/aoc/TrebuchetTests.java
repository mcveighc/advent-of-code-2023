package com.oc.aoc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TrebuchetTests {

    // Part 1
    @Test
    void getCalibrationValue_returns_first_and_last_digit() {
        int digits = Trebuchet.getCalibrationValue("a1b2c3d4e5f");
        assertEquals(15, digits);
    }

    @Test
    void getCalibrationValue_whenOnlyOneDigitExists_returns_expectedResult() {
        int digits = Trebuchet.getCalibrationValue("treb7uchet");
        assertEquals(77, digits);
    }

    @Test
    void getCalibrationValue_whenNoDigitsExist_returns_0() {
        int digits = Trebuchet.getCalibrationValue("abc");
        assertEquals(0, digits);
    }

    @Test
    void getCalibrationValue_withNumberKeywords_returns_19() {
        int digits = Trebuchet.getCalibrationValue("oneight");
        assertEquals(18, digits);
    }

    @Test
    void calibrateDocument_returns_expectedResult() {
        String[] calibrationReading = TestHelpers.calibrationDocument;
        int calibratedDocumentTotal = Trebuchet.calibrateDocument(calibrationReading);

        assertEquals(54418, calibratedDocumentTotal);
    }

    // Part 2
    @Test
    void normaliseCalibrationReading_returns_expectedValues() {
        String result = Trebuchet.normaliseCalibrationReading("onetwothreefourfivesixseveneightnine");
        assertEquals("1e2o3e4r5e6x7n8t9e", result);
    }

    @Test
    void normaliseCalibrationReading_withMultiplesReturns_expectedValues() {
        String result = Trebuchet.normaliseCalibrationReading("eightwothree");
        assertEquals("82o3e", result);
    }

}
