package com.oc.aoc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import com.oc.aoc.helpers.GearRatiosTestHelpers;
import com.oc.aoc.model.GearRationEnginePart;

public class GearRatiosTests {

    // Examples and Input tests
    @Test
    void getEnginePartsSum_withExamplePart1_returns_expectedResult() {
        String scheamtic = GearRatiosTestHelpers.examplePart1;
        int sum = GearRatios.getEnginePartsSum(scheamtic);

        assertEquals(4361, sum);
    }

    @Test
    void getGearRatio_withExamplePart2_returns_expectedResult() {
        String scheamtic = GearRatiosTestHelpers.examplePart2;
        int sum = GearRatios.getGearRatiosSum(scheamtic);

        assertEquals(467835, sum);
    }

    @Test
    void getGearRatio_withInput_returns_expectedResult() {
        String scheamtic = GearRatiosTestHelpers.exampleInput;
        int sum = GearRatios.getGearRatiosSum(scheamtic);

        assertEquals(67779080, sum);
    }

    @Test
    void getEnginePartsSum_withInput_returns_expectedResult() {
        String scheamtic = GearRatiosTestHelpers.exampleInput;
        int sum = GearRatios.getEnginePartsSum(scheamtic);

        assertEquals(512794, sum);
    }

    // initSchematic Tests
    @Test
    void initSchematic_returns_expectedSizes() {
        String exampleGrid = GearRatiosTestHelpers.examplePart1;
        char[][] grid = GearRatios.initSchematic(exampleGrid);

        assertEquals(10, grid.length);
        assertEquals(10, grid[0].length);
    }

    @Test
    void initSchematic_plotsCharacters_correctly() {
        String exampleGrid = GearRatiosTestHelpers.examplePart1;
        char[][] grid = GearRatios.initSchematic(exampleGrid);

        assertEquals('7', grid[0][2]);
        assertEquals('*', grid[1][3]);
        assertEquals('8', grid[9][7]);
    }

    // getEnginePartsFromSchematic tests
    @Test
    void getEnginePartsFromSchematic_returns_expectedParts() {
        String exampleGrid = GearRatiosTestHelpers.examplePart1;
        char[][] grid = GearRatios.initSchematic(exampleGrid);

        List<GearRationEnginePart> numbers = GearRatios.getGearEngineParts(grid);

        GearRationEnginePart enginePart1 = numbers.get(0);
        assertEquals(467, enginePart1.getPartNumber());
        assertTrue(enginePart1.hasAdjacentSymbol());

        GearRationEnginePart enginePart2 = numbers.get(1);
        assertEquals(114, enginePart2.getPartNumber());
        assertFalse(enginePart2.hasAdjacentSymbol());
    }

    // hasAdjacent Symbols success tests
    @Test
    void hasAdjacentSymbol_returnsTrue_whenSymbolAt_yPlusOne() {
        String exampleGrid = "..........\n" +
                ".467*.....\n" +
                "..........\n";

        int xPos = 1;
        int yPos = 3;
        char[][] grid = GearRatios.initSchematic(exampleGrid);
        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertTrue(hasAdjacentSymbol);
    }

    @Test
    void hasAdjacentSymbol_returnsTrue_whenSymbolAt_xPlusOne_yPlusOne() {
        String exampleGrid = "..........\n" +
                ".467......\n" +
                "....*.....\n";

        int xPos = 1;
        int yPos = 3;
        char[][] grid = GearRatios.initSchematic(exampleGrid);

        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertTrue(hasAdjacentSymbol);
    }

    @Test
    void hasAdjacentSymbol_returnsTrue_whemSymbolAt_xPlusOne() {
        String exampleGrid = "..........\n" +
                ".467......\n" +
                "..*.......\n";

        int xPos = 1;
        int yPos = 2;
        char[][] grid = GearRatios.initSchematic(exampleGrid);

        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertTrue(hasAdjacentSymbol);
    }

    @Test
    void hasAdjacentSymbol_returnsTrue_whenSymbolAt_xPlusOne_yMinusOne() {
        String exampleGrid = "..........\n" +
                ".467......\n" +
                "*.........\n";

        int xPos = 1;
        int yPos = 1;
        char[][] grid = GearRatios.initSchematic(exampleGrid);

        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertTrue(hasAdjacentSymbol);
    }

    @Test
    void hasAdjacentSymbol_returnsTrue_whenSymbolAt_yMinusOne() {
        String exampleGrid = "..........\n" +
                "*467......\n" +
                "..........\n";

        int xPos = 1;
        int yPos = 1;
        char[][] grid = GearRatios.initSchematic(exampleGrid);

        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertTrue(hasAdjacentSymbol);
    }

    @Test
    void hasAdjacentSymbol_returnsTrue_whenSymbolAt_xMinusOne_yMinusOne() {
        String exampleGrid = "*.........\n" +
                ".467......\n" +
                "..........\n";

        int xPos = 1;
        int yPos = 1;
        char[][] grid = GearRatios.initSchematic(exampleGrid);

        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertTrue(hasAdjacentSymbol);
    }

    @Test
    void hasAdjacentSymbol_returnsTrue_whenSymbolAt_xMinusOne() {
        String exampleGrid = "..*.......\n" +
                ".467......\n" +
                "..........\n";

        int xPos = 1;
        int yPos = 2;
        char[][] grid = GearRatios.initSchematic(exampleGrid);

        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertTrue(hasAdjacentSymbol);
    }

    @Test
    void hasAdjacentSymbol_returnsTrue_whenSymbolAt_xMinusOne_yPlusOne() {
        String exampleGrid = "....*.....\n" +
                ".467......\n" +
                "..........\n";

        int xPos = 1;
        int yPos = 3;
        char[][] grid = GearRatios.initSchematic(exampleGrid);

        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertTrue(hasAdjacentSymbol);
    }

    // // hasAdjacentSymbols boundary test
    @Test
    void hasAdjacentSymbol_returnsFalse_rightBorder() {
        String exampleGrid = "..........\n" +
                ".........1\n" +
                "..........\n";

        int xPos = 1;
        int yPos = 9;
        char[][] grid = GearRatios.initSchematic(exampleGrid);
        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertFalse(hasAdjacentSymbol);
    }

    @Test
    void hasAdjacentSymbol_returnsFalse_topRightBorder() {
        String exampleGrid = ".........1\n" +
                "..........\n" +
                "..........\n";

        int xPos = 0;
        int yPos = 9;
        char[][] grid = GearRatios.initSchematic(exampleGrid);
        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertFalse(hasAdjacentSymbol);
    }

    @Test
    void hasAdjacentSymbol_returnsFalse_topBorder() {
        String exampleGrid = ".....1....\n" +
                "..........\n" +
                "..........\n";

        int xPos = 0;
        int yPos = 6;
        char[][] grid = GearRatios.initSchematic(exampleGrid);
        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertFalse(hasAdjacentSymbol);
    }

    @Test
    void hasAdjacentSymbol_returnsFalse_topLeftBorder() {
        String exampleGrid = "1.........\n" +
                "..........\n" +
                "..........\n";

        int xPos = 0;
        int yPos = 0;
        char[][] grid = GearRatios.initSchematic(exampleGrid);
        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertFalse(hasAdjacentSymbol);
    }

    @Test
    void hasAdjacentSymbol_returnsFalse_leftBorder() {
        String exampleGrid = "..........\n" +
                "1.........\n" +
                "..........\n";

        int xPos = 1;
        int yPos = 0;
        char[][] grid = GearRatios.initSchematic(exampleGrid);
        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertFalse(hasAdjacentSymbol);
    }

    @Test
    void hasAdjacentSymbol_returnsFalse_bottomLeftBorder() {
        String exampleGrid = "..........\n" +
                "..........\n" +
                "1.........\n";

        int xPos = 2;
        int yPos = 0;
        char[][] grid = GearRatios.initSchematic(exampleGrid);
        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertFalse(hasAdjacentSymbol);
    }

    @Test
    void hasAdjacentSymbol_returnsFalse_bottomBorder() {
        String exampleGrid = "..........\n" +
                "..........\n" +
                "....1.....\n";

        int xPos = 2;
        int yPos = 4;
        char[][] grid = GearRatios.initSchematic(exampleGrid);
        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertFalse(hasAdjacentSymbol);
    }

    @Test
    void hasAdjacentSymbol_returnsFalse_bottomRightBorder() {
        String exampleGrid = "..........\n" +
                "..........\n" +
                ".........1\n";

        int xPos = 2;
        int yPos = 9;
        char[][] grid = GearRatios.initSchematic(exampleGrid);
        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertFalse(hasAdjacentSymbol);
    }

    // getRatioGearEnginePart tests
    @Test
    void getRatioGearEnginePart_startingAtleftMostDigit_returns_expectedResult() {
        String schematic = "..........\n" +
                "...123....\n" +
                "..........\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);
        Integer enginePart = GearRatios.getGearRatioEnginePart(parsedSchematic, 1, 3);

        assertEquals(123, enginePart);
    }

    @Test
    void getRatioGearEnginePart_startingAtMiddleDigit_returns_expectedResult() {
        String schematic = "..........\n" +
                "...123....\n" +
                "..........\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);
        Integer enginePart = GearRatios.getGearRatioEnginePart(parsedSchematic, 1, 4);

        assertEquals(123, enginePart);
    }

    @Test
    void getRatioGearEnginePart_startingAtRightMostDigit_returns_expectedResult() {
        String schematic = "..........\n" +
                "...123....\n" +
                "..........\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);
        Integer enginePart = GearRatios.getGearRatioEnginePart(parsedSchematic, 1, 5);

        assertEquals(123, enginePart);
    }

    @Test
    void getRatioGearEnginePart_leftBorder_returns_expectedResult() {
        String schematic = "..........\n" +
                "123.......\n" +
                "..........\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);
        Integer enginePart = GearRatios.getGearRatioEnginePart(parsedSchematic, 1, 0);

        assertEquals(123, enginePart);

    }

    @Test
    void getRatioGearEnginePart_rightBorder_returns_expectedResult() {
        String schematic = "..........\n" +
                ".......123\n" +
                "..........\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);
        Integer enginePart = GearRatios.getGearRatioEnginePart(parsedSchematic, 1, 9);

        assertEquals(123, enginePart);
    }

    @Test
    void getRatioGearPairs_horizontal_returns_expectedResult() {
        String schematic = "..........\n" +
                "...321*123\n" +
                "..........\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);

        List<Pair<Integer, Integer>> pairs = GearRatios.getRatioGearPairs(parsedSchematic);
        assertEquals(1, pairs.size());
        assertEquals(321, pairs.get(0).getValue0());
        assertEquals(123, pairs.get(0).getValue1());
    }

    @Test
    void getRatioGearPairs_vertical_returns_expectedResult() {
        String schematic = "...321....\n" +
                "....*.....\n" +
                "...123....\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);

        List<Pair<Integer, Integer>> pairs = GearRatios.getRatioGearPairs(parsedSchematic);
        assertEquals(1, pairs.size());
        assertEquals(321, pairs.get(0).getValue0());
        assertEquals(123, pairs.get(0).getValue1());
    }

    @Test
    void getRatioGearPairs_diag1_returns_expectedResult() {
        String schematic = "321......\n" +
                "...*.....\n" +
                "....123...\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);

        List<Pair<Integer, Integer>> pairs = GearRatios.getRatioGearPairs(parsedSchematic);
        assertEquals(1, pairs.size());
        assertEquals(321, pairs.get(0).getValue0());
        assertEquals(123, pairs.get(0).getValue1());
    }

    @Test
    void getRatioGearPairs_diag2_returns_expectedResult() {
        String schematic = "..321....\n" +
                ".....*...\n" +
                "......123\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);

        List<Pair<Integer, Integer>> pairs = GearRatios.getRatioGearPairs(parsedSchematic);
        assertEquals(1, pairs.size());
        assertEquals(321, pairs.get(0).getValue0());
        assertEquals(123, pairs.get(0).getValue1());
    }

    @Test
    void getRatioGearPairs_diag3_returns_expectedResult() {
        String schematic = "......321\n" +
                ".....*...\n" +
                "..123....\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);

        List<Pair<Integer, Integer>> pairs = GearRatios.getRatioGearPairs(parsedSchematic);
        assertEquals(1, pairs.size());
        assertEquals(321, pairs.get(0).getValue0());
        assertEquals(123, pairs.get(0).getValue1());
    }

    @Test
    void getRatioGearPairs_diag4_returns_expectedResult() {
        String schematic = 
                ".....45..\n" +
                "....*....\n" +
                "..127....\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);

        List<Pair<Integer, Integer>> pairs = GearRatios.getRatioGearPairs(parsedSchematic);
        assertEquals(1, pairs.size());
        assertEquals(45, pairs.get(0).getValue0());
        assertEquals(127, pairs.get(0).getValue1());
    }

    @Test
    void getRatioGearPairs_example5_returns_expectedResult() {
        String schematic = "..45.....\n" +
                "....*....\n" +
                "..127....\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);

        List<Pair<Integer, Integer>> pairs = GearRatios.getRatioGearPairs(parsedSchematic);
        assertEquals(1, pairs.size());
        assertEquals(45, pairs.get(0).getValue0());
        assertEquals(127, pairs.get(0).getValue1());
    }

    @Test
    void getRatioGearPairs_example6_returns_expectedResult() {
        String schematic = ".........\n" +
                "....*45..\n" +
                ".127.....\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);

        List<Pair<Integer, Integer>> pairs = GearRatios.getRatioGearPairs(parsedSchematic);
        assertEquals(1, pairs.size());
        assertEquals(127, pairs.get(0).getValue0());
        assertEquals(45, pairs.get(0).getValue1());
    }

    @Test
    void getRatioGearPairs_example7_returns_expectedResult() {
        String schematic = ".........\n" +
                "....*....\n" +
                ".127.45..\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);

        List<Pair<Integer, Integer>> pairs = GearRatios.getRatioGearPairs(parsedSchematic);
        assertEquals(1, pairs.size());
        assertEquals(127, pairs.get(0).getValue0());
        assertEquals(45, pairs.get(0).getValue1());
    }

    @Test
    void getRatioGearPairs_example8_returns_expectedResult() {
        String schematic = 
                ".127...\n" +
                "....*....\n" +
                "....45....\n";

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);

        List<Pair<Integer, Integer>> pairs = GearRatios.getRatioGearPairs(parsedSchematic);
        assertEquals(1, pairs.size());
        assertEquals(127, pairs.get(0).getValue0());
        assertEquals(45, pairs.get(0).getValue1());
    }
}
