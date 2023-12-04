package com.oc.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.oc.aoc.model.GearRationEnginePart;

public class GearRatios {

    public static char[][] initSchematic(String exampleGrid) {
        String[] rows = exampleGrid.split("\n");

        int rowSize = rows.length;
        int columnSize = rows[0].length();

        char[][] grid = new char[rowSize][columnSize];
        for (int rowPos = 0; rowPos < rowSize; rowPos++) {
            char[] row = rows[rowPos].toCharArray();
            for (int colPos = 0; colPos < columnSize; colPos++) {
                grid[rowPos][colPos] = row[colPos];
            }
        }

        return grid;
    }

    public static List<GearRationEnginePart> getGearEngineParts(char[][] grid) {
        List<GearRationEnginePart> result = new ArrayList<>();

        String enginePartNumber = "";
        boolean hasAdjacentSymbol = false;

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                char item = grid[x][y];
                boolean isNumber = Character.isDigit(item);

                if (isNumber) {
                    enginePartNumber = enginePartNumber + String.valueOf(item);
                    if (!hasAdjacentSymbol) {
                        hasAdjacentSymbol = GearRatios.hasAdjacentSymbol(grid, x, y);
                    }
                } else {
                    if (enginePartNumber.isEmpty()) {
                        continue;
                    }

                    int enginePart = Integer.parseInt(enginePartNumber);
                    GearRationEnginePart gearRationEnginePart = new GearRationEnginePart(enginePart, hasAdjacentSymbol);
                    result.add(gearRationEnginePart);

                    enginePartNumber = "";
                    hasAdjacentSymbol = false;
                }
            }
        }
        return result;
    }

    public static boolean hasAdjacentSymbol(char[][] grid, int xPos, int yPos) {
        boolean isTopBorder = xPos == 0;
        boolean isLeftBorder = yPos == 0;
        boolean isBottomBorder = xPos == grid.length - 1;
        boolean isRightBorder = yPos == grid[0].length - 1;

        List<Character> adjacentChars = new ArrayList<>();

        if (!isBottomBorder) {
            char xPlusOne = grid[xPos + 1][yPos];
            adjacentChars.add(xPlusOne);
        }
        if (!isBottomBorder && !isRightBorder) {
            char xPlusOneYPlusOne = grid[xPos + 1][yPos + 1];
            adjacentChars.add(xPlusOneYPlusOne);
        }

        if (!isBottomBorder && !isLeftBorder) {
            char xPlusOneYMinusOne = grid[xPos + 1][yPos - 1];
            adjacentChars.add(xPlusOneYMinusOne);
        }

        if (!isTopBorder) {
            char xMinusOne = grid[xPos - 1][yPos];
            adjacentChars.add(xMinusOne);
        }

        if (!isTopBorder && !isLeftBorder) {
            char xMinusOneYMinusOne = grid[xPos - 1][yPos - 1];
            adjacentChars.add(xMinusOneYMinusOne);
        }

        if (!isTopBorder && !isRightBorder) {
            char xMinueOneYPlusOne = grid[xPos - 1][yPos + 1];
            adjacentChars.add(xMinueOneYPlusOne);
        }

        if (!isRightBorder) {
            char yPlusOne = grid[xPos][yPos + 1];
            adjacentChars.add(yPlusOne);
        }

        if (!isLeftBorder) {
            char yMinusOne = grid[xPos][yPos - 1];
            adjacentChars.add(yMinusOne);
        }

        Predicate<? super Character> isAdjacentPredicate = c -> !Character.isLetterOrDigit(c) && c != '.';

        return adjacentChars.stream().anyMatch(isAdjacentPredicate);
    }

    public static int getEnginePartsSum(String schematic) {
        int result = 0;

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);
        List<GearRationEnginePart> parts = GearRatios.getGearEngineParts(parsedSchematic);

        for(GearRationEnginePart part : parts) {
            if(part.hasAdjacentSymbol()) {
                result = result + part.getPartNumber();
            }
        }

        return result;

    }

}
