package com.oc.aoc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.oc.aoc.helpers.ScratchcardsTestHelpers;
import com.oc.aoc.model.Scratchcard;

public class ScratchcardsTests {
    
    @Test
    void initCard_hasExpectedCardNumber() {
        String card = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";

        Scratchcard scratchcard = Scratchcards.initCard(card);
        assertEquals(1, scratchcard.getCardNumber());
    }

    @Test
    void initCard_hasMatchingWinningNumbers() {
        String card = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
        Set<Integer> expectedResult = Set.of(41, 48, 83, 86, 17);

        Scratchcard scratchcard = Scratchcards.initCard(card);
        assertTrue(expectedResult.equals(scratchcard.getWinningNumbers()));
    }

    @Test
    void initCard_hasMatchingNumbers() {
        String card = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
        Set<Integer> expectedResult = Set.of(83, 86, 6, 31, 17, 9, 48, 53);

        Scratchcard scratchcard = Scratchcards.initCard(card);
        assertTrue(expectedResult.equals(scratchcard.getNumbers()));
    }

    @Test
    void getPointsForCard_returns_expectedPoints() {
        String card = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
        Scratchcard scratchcard = Scratchcards.initCard(card);
        int totalPoints = Scratchcards.getPointsForCard(scratchcard);

        assertEquals(8, totalPoints);
    }


    @Test
    void getTotalPointsForCards_forExample_returns_expectedPoints() {
        String cardsToParse = ScratchcardsTestHelpers.part1Example;
        int totalPoints = Scratchcards.getPointsForCards(cardsToParse);

        assertEquals(13, totalPoints);
    }

    @Test
    void getTotalPointsForCards_forInput_returns_expectedPoints() {
        String cardsToParse = ScratchcardsTestHelpers.input;
        int totalPoints = Scratchcards.getPointsForCards(cardsToParse);

        assertEquals(20667, totalPoints);
    }

    @Test
    void getTotalCardsWithCopies_forExample_returnsExpectedResult() {
        String cardsToParse = ScratchcardsTestHelpers.part1Example;
        int totalCards = Scratchcards.getTotalCardsWithCopies(cardsToParse);

        assertEquals(30, totalCards);
    }

     @Test
    void getTotalCardsWithCopies_forInput_returnsExpectedResult() {
        String cardsToParse = ScratchcardsTestHelpers.input;
        int totalCards = Scratchcards.getTotalCardsWithCopies(cardsToParse);

        assertEquals(5833065, totalCards);
    }
}
