package com.oc.aoc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.javatuples.Pair;

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

    public static int getEnginePartsSum(String schematic) {
        int result = 0;

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);
        List<GearRationEnginePart> parts = GearRatios.getGearEngineParts(parsedSchematic);

        for (GearRationEnginePart part : parts) {
            if (part.hasAdjacentSymbol()) {
                result = result + part.getPartNumber();
            }
        }

        return result;
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
        Function<Character, Boolean> isSymbolFunc = (c) -> !Character.isLetterOrDigit(c) && c != '.';
        int totalAdjacentsRequired = 1;

        List<Pair<Integer, Integer>> matchedAdjacents = getMatchingAdjacentPositions(grid, xPos, yPos,
                isSymbolFunc, totalAdjacentsRequired);

        return matchedAdjacents.size() == totalAdjacentsRequired;
    }

    private static Map<String, Pair<Integer, Integer>> getAdjacents(char[][] schematic, int x, int y) {
        boolean isTopBorder = x == 0;
        boolean isLeftBorder = y == 0;
        boolean isBottomBorder = x == schematic.length - 1;
        boolean isRightBorder = y == schematic[0].length - 1;

        Map<String, Pair<Integer, Integer>> adjacents = new LinkedHashMap<>();

        if (!isTopBorder) {
            adjacents.put("top", new Pair<Integer, Integer>(x - 1, y));
        }
        if (!isTopBorder && !isLeftBorder) {
            adjacents.put("top-left", new Pair<Integer, Integer>(x + -1, y - 1));
        }
        if (!isTopBorder && !isRightBorder) {
            adjacents.put("top-right", new Pair<Integer, Integer>(x - 1, y + 1));
        }

        if (!isBottomBorder) {
            adjacents.put("bottom", new Pair<Integer, Integer>(x + 1, y));
        }
        if (!isBottomBorder && !isLeftBorder) {
            adjacents.put("bottom-left", new Pair<Integer, Integer>(x + 1, y - 1));
        }
        if (!isBottomBorder && !isRightBorder) {
            adjacents.put("bottom-right", new Pair<Integer, Integer>(x + 1, y + 1));
        }

        if (!isLeftBorder) {
            adjacents.put("left", new Pair<Integer, Integer>(x, y - 1));
        }

        if (!isRightBorder) {
            adjacents.put("right", new Pair<Integer, Integer>(x, y + 1));
        }

        return adjacents;
    }

    private static List<Pair<Integer, Integer>> getMatchingAdjacentPositions(char[][] grid,
            int x, int y,
            Function<Character, Boolean> adjacentMatcherFunc,
            int maxAdjacents) {

        Map<String, Pair<Integer, Integer>> adjacents = getAdjacents(grid, x, y);
        List<Pair<Integer, Integer>> matchedAdjacents = new ArrayList<>();

        boolean hasMatchedTop = false;
        boolean hasMatchedBottom = false;
        for (String adjacentKey : adjacents.keySet()) {
            boolean isTopAdjacent = adjacentKey.contains("top");
            boolean isBottomeAdjacent = adjacentKey.contains("bottom");

            if (matchedAdjacents.size() == maxAdjacents) {
                continue;
            }

            if (isTopAdjacent && hasMatchedTop) {
                continue;
            }

            if (isBottomeAdjacent && hasMatchedBottom) {
                continue;
            }

            Pair<Integer, Integer> adjacentPos = adjacents.get(adjacentKey);

            int adjacentX = adjacentPos.getValue0();
            int adjacentY = adjacentPos.getValue1();

            Character adjacentChar = grid[adjacentX][adjacentY];

            boolean hasMatchingAdjacentChar = adjacentMatcherFunc.apply(adjacentChar);
            if (hasMatchingAdjacentChar) {
                matchedAdjacents.add(adjacentPos);

            }

            hasMatchedTop = adjacentKey.equals("top") && hasMatchingAdjacentChar;
            hasMatchedBottom = adjacentKey.equals("bottom") && hasMatchingAdjacentChar;
        }

        return matchedAdjacents;
    }

    public static int getGearRatiosSum(String schematic) {
        int result = 0;

        char[][] parsedSchematic = GearRatios.initSchematic(schematic);
        List<Pair<Integer, Integer>> gearRatioPairs = GearRatios.getRatioGearPairs(parsedSchematic);

        for (Pair<Integer, Integer> gearRatioPair : gearRatioPairs) {
            int leftPart = gearRatioPair.getValue0();
            int rightPart = gearRatioPair.getValue1();
            int sum = leftPart * rightPart;

            result = result + sum;
        }

        return result;
    }

    public static List<Pair<Integer, Integer>> getRatioGearPairs(char[][] parsedSchematic) {
        int rowLength = parsedSchematic.length;
        int colLength = parsedSchematic[0].length;
        Function<Character, Boolean> func = (c) -> Character.isDigit(c);

        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for (int x = 0; x < rowLength; x++) {
            for (int y = 0; y < colLength; y++) {
                char item = parsedSchematic[x][y];
                if (item != '*') {
                    continue;
                }
                List<Pair<Integer, Integer>> matchingAdjacents = getMatchingAdjacentPositions(parsedSchematic,
                        x, y, func, 2);

                if (matchingAdjacents.size() != 2) {
                    continue;
                }

                int enginePart1 = getGearRatioEnginePart(parsedSchematic, matchingAdjacents.get(0).getValue0(),
                        matchingAdjacents.get(0).getValue1());
                int enginePart2 = getGearRatioEnginePart(parsedSchematic, matchingAdjacents.get(1).getValue0(),
                        matchingAdjacents.get(1).getValue1());

                Pair<Integer, Integer> gearRatioPair = Pair.with(enginePart1, enginePart2);
                pairs.add(gearRatioPair);
            }
        }

        return pairs;
    }

    public static Integer getGearRatioEnginePart(char[][] parsedSchematic, int xPos, int yPos) {
        char digit = parsedSchematic[xPos][yPos];

        int borderLeft = 0;
        int borderRight = parsedSchematic[0].length - 1;

        boolean leftDigitExists = true;
        boolean rightDigitExists = true;

        boolean hasReachedLeftEnd = false;
        boolean hasReachedRightEnd = false;

        int index = 1;

        String result = String.valueOf(digit);

        while (leftDigitExists == true || rightDigitExists == true) {
            int leftPos = yPos - index;
            int rightPos = yPos + index;

            char leftItem = leftPos < borderLeft ? '.' : parsedSchematic[xPos][leftPos];
            char rightItem = rightPos > borderRight ? '.' : parsedSchematic[xPos][rightPos];

            leftDigitExists = Character.isDigit(leftItem);
            rightDigitExists = Character.isDigit(rightItem);

            if (!hasReachedLeftEnd) {
                hasReachedLeftEnd = !leftDigitExists;
            }

            if (!hasReachedRightEnd) {
                hasReachedRightEnd = !rightDigitExists;
            }

            if (leftDigitExists && !hasReachedLeftEnd) {
                result = String.valueOf(leftItem) + result;
            }

            if (rightDigitExists && !hasReachedRightEnd) {
                result = result + String.valueOf(rightItem);
            }

            index++;
        }

        return Integer.parseInt(result);

    }
}
