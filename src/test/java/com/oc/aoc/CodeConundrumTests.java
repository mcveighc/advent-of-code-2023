package com.oc.aoc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.oc.aoc.model.CodeConundrumGame;
import com.oc.aoc.model.CodeConundrumGameSet;

public class CodeConundrumTests { 

    @Test
    void getValidGameTotal_forExample_returns_expectedResult() {
        String[] gameStr = CodeConundrumTestHelpers.gamesExample1;
        int total = CodeConundrum.getValidGameTotal(gameStr);

        assertEquals(8, total);
    }

    @Test
    void getValidGameTotal_forInput_returns_expectedResult() {
        String[] gameStr = CodeConundrumTestHelpers.gamesInput;
        int total = CodeConundrum.getValidGameTotal(gameStr);

        assertEquals(2207, total);
    }

    @Test
    void getMaxCubesPower_forExample_returns_expectedResult() {
        String[] gameStr = CodeConundrumTestHelpers.gamesExample2;
        int total = CodeConundrum.getMaxCubesPower(gameStr);

        assertEquals(2286, total);
    }

    @Test
    void getMaxCubesPower_forInput_returns_expectedResult() {
        String[] gameStr = CodeConundrumTestHelpers.gamesInput;
        int total = CodeConundrum.getMaxCubesPower(gameStr);

        assertEquals(62241, total);
    }
 
    @Test
    void isValidGame_when_coloursLessThanBoundary_returnsTrue() {
        String gameStr = "Game 999: 1 red, 1 blue, 1 green";
        CodeConundrumGame game = CodeConundrum.parseGame(gameStr);

        boolean isValid = CodeConundrum.isValidGame(game);
        assertTrue(isValid);
    }


    @Test
    void isValidGame_when_coloursOnBoundary_returnsTrue() {
        String gameStr = "Game 999: 12 red, 14 blue, 13 green";
        CodeConundrumGame game = CodeConundrum.parseGame(gameStr);

        boolean isValid = CodeConundrum.isValidGame(game);
        assertTrue(isValid);
    }

    @Test
    void isValidGame_when_coloursOnBoundary_multipleSets_returnsTrue() {
        String gameStr = "Game 999: 12 red, 14 blue, 13 green; 12 red, 14 blue, 13 green";
        CodeConundrumGame game = CodeConundrum.parseGame(gameStr);

        boolean isValid = CodeConundrum.isValidGame(game);
        assertTrue(isValid);
    }

    @Test
    void isValidGame_when_redOverBoundary12_returnsFalse() {
        String gameStr = "Game 999: 13 red, 1 blue, 1 green";
        CodeConundrumGame game = CodeConundrum.parseGame(gameStr);

        boolean isValid = CodeConundrum.isValidGame(game);
        assertFalse(isValid);
    }

    @Test
    void isValidGame_when_blueOverBoundary14_returnsFalse() {
        String gameStr = "Game 999: 1 red, 15 blue, 1 green";
        CodeConundrumGame game = CodeConundrum.parseGame(gameStr);

        boolean isValid = CodeConundrum.isValidGame(game);
        assertFalse(isValid);
    }

    @Test
    void isValidGame_when_greenOverBoundary13_returnsFalse() {
        String gameStr = "Game 999: 1 red, 1 blue, 14 green";
        CodeConundrumGame game = CodeConundrum.parseGame(gameStr);

        boolean isValid = CodeConundrum.isValidGame(game);
        assertFalse(isValid);
    }


    @Test
    void parseGame_returns_expectedGame() {
        String gameStr = "Game 999: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
        CodeConundrumGame game = CodeConundrum.parseGame(gameStr);

        assertEquals(999, game.getId());
        assertEquals(2, game.getGameSets().size());
        
    }

    @Test
    void parseGame_returns_expectedGameSet() {
        String gameStr = "Game 95: 2 green, 6 red, 13 blue; 5 red, 12 green, 12 blue; 18 blue, 8 red, 4 green; 7 red, 6 green, 17 blue; 4 green, 9 red, 6 blue; 10 red, 1 green, 4 blue";
        CodeConundrumGame game = CodeConundrum.parseGame(gameStr);

        assertEquals(95, game.getId());
        assertEquals(6, game.getGameSets().size());
        
    }

    @Test
    void parseGame_returns_expectedGameSetValues() {
        String gameStr = "Game 999: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
        CodeConundrumGame game = CodeConundrum.parseGame(gameStr);

        CodeConundrumGameSet gameSet1 = game.getGameSets().get(0);
        assertEquals(6, gameSet1.getRed());
        assertEquals(1, gameSet1.getBlue());
        assertEquals(3, gameSet1.getGreen());

        CodeConundrumGameSet gameSet2 = game.getGameSets().get(1);
        assertEquals(1, gameSet2.getRed());
        assertEquals(2, gameSet2.getBlue());
        assertEquals(2, gameSet2.getGreen());
    }
}
