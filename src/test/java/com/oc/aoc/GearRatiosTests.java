package com.oc.aoc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import com.oc.aoc.helpers.GearRatiosTestHelpers;
import com.oc.aoc.model.GearRationEnginePart;

public class GearRatiosTests {

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
        String exampleGrid = 
            "..........\n" +
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
        String exampleGrid =
            "..........\n" +
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
        String exampleGrid = 
            "..........\n" +
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
        String exampleGrid =
            "..........\n" +
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
        String exampleGrid =
            "..........\n" +
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
        String exampleGrid =
            "*.........\n" +
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
        String exampleGrid =
            "..*.......\n" +
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
        String exampleGrid =
            "....*.....\n" +
            ".467......\n" +
            "..........\n";

        int xPos = 1;
        int yPos = 3;
        char[][] grid = GearRatios.initSchematic(exampleGrid);

        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertTrue(hasAdjacentSymbol);
    }
    
    // hasAdjacentSymbols boundary test
    @Test
    void hasAdjacentSymbol_returnsFalse_rightBorder() {
        String exampleGrid = 
            "..........\n" +
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
        String exampleGrid = 
            ".........1\n" +
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
        String exampleGrid = 
            ".....1....\n" +
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
        String exampleGrid = 
            "1.........\n" +
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
        String exampleGrid = 
            "..........\n" +
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
        String exampleGrid = 
            "..........\n" +
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
        String exampleGrid = 
            "..........\n" +
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
        String exampleGrid = 
            "..........\n" +
            "..........\n" +
            ".........1\n";

        int xPos = 2;
        int yPos = 9;
        char[][] grid = GearRatios.initSchematic(exampleGrid);
        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertFalse(hasAdjacentSymbol);
    }


    // hasAdjacentSymbols failure tests
    @Test
    void hasAdjacentSymbol_returnsFalse_whenSymbolNotAdjacent() {
        String exampleGrid =
            "..*....\n" +
            "*...*..\n" +
            "*.6.*..\n" +
            "*...*..\n" +
            "..*....\n";

        int xPos = 2;
        int yPos = 2;
        char[][] grid = GearRatios.initSchematic(exampleGrid);

        boolean hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, xPos, yPos);

        assertFalse(hasAdjacentSymbol);
    }

    @Test
    void getEnginePartsSum_examplePart1_returns_expectedResult() {
        String scheamtic = GearRatiosTestHelpers.examplePart1;
        int sum = GearRatios.getEnginePartsSum(scheamtic);

        assertEquals(4361, sum);
    }

    @Test
    void getEnginePartsSum_input_returns_expectedResult() {
        String scheamtic = GearRatiosTestHelpers.exampleInput;
        int sum = GearRatios.getEnginePartsSum(scheamtic);

        assertEquals(512794, sum);
    }
}
