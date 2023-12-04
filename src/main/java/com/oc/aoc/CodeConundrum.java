package com.oc.aoc;

import java.util.ArrayList;
import java.util.List;

import com.oc.aoc.model.CodeConundrumGame;
import com.oc.aoc.model.CodeConundrumGameSet;
import com.oc.aoc.model.CodeConundrumGameSettings;

public class CodeConundrum {

    public static int getValidGameTotal(String[] games) {
        int result = 0;
        for (String gameStr : games) {
            CodeConundrumGame game = parseGame(gameStr);
            if (CodeConundrum.isValidGame(game)) {
                result = result + game.getId();
            }
        }
        return result;
    }

    public static boolean isValidGame(CodeConundrumGame game) {
        CodeConundrumGameSettings settings = game.getSettings();
        return (settings.getRedCubesTotal() <= 12) && (settings.getBlueCubesTotal() <= 14)
                && (settings.getGreenCubesTotal() <= 13);
    }

    public static int getMaxCubesPower(String[] games) {
        int result = 0;
        for (String gameStr : games) {
            CodeConundrumGame game = parseGame(gameStr);
            CodeConundrumGameSettings gameSettings = game.getSettings();
            result = result + (gameSettings.getRedCubesTotal() * gameSettings.getBlueCubesTotal() * gameSettings.getGreenCubesTotal());
            
        }
        return result;
    }

    public static CodeConundrumGame parseGame(String game) {
        int gameId = parseGameId(game);
        List<CodeConundrumGameSet> gameSets = parseGameSets(game);
        CodeConundrumGameSettings settings = initGameSettings(gameSets);

        return new CodeConundrumGame(gameId, gameSets, settings);
    }

    private static int parseGameId(String game) {
        final int gameIdDelimiterIndex = game.indexOf(':');
        final String id = game.substring(5, gameIdDelimiterIndex);

        return Integer.parseInt(id);
    }

    private static List<CodeConundrumGameSet> parseGameSets(String games) {
        String[] gameSets = games
                .split(":")[1]
                .split(";");

        List<CodeConundrumGameSet> result = new ArrayList<>();
        for (String gameSet : gameSets) {
            CodeConundrumGameSet item = parseGameSet(gameSet.trim());
            result.add(item);
        }

        return result;
    }

    private static CodeConundrumGameSet parseGameSet(String gameSetStr) {
        String[] colourCubeCounts = gameSetStr.split(", ");
        int red = 0;
        int blue = 0;
        int green = 0;

        for (String colourCubeCountItem : colourCubeCounts) {
            String[] colourCubesPair = colourCubeCountItem.split(" ");

            int cubeCount = Integer.parseInt(colourCubesPair[0]);
            String cubeColour = colourCubesPair[1].trim().toLowerCase();

            if (cubeColour.equals("red")) {
                red = cubeCount;
            } else if (cubeColour.equals("blue")) {
                blue = cubeCount;
            } else if (cubeColour.equals("green")) {
                green = cubeCount;
            } else {
                throw new IllegalArgumentException(cubeColour);
            }
        }

        return new CodeConundrumGameSet(blue, red, green);
    }

    private static CodeConundrumGameSettings initGameSettings(List<CodeConundrumGameSet> gameSets) {
        int maxRed = 0;
        int maxBlue = 0;
        int maxGreen = 0;

        for (CodeConundrumGameSet gameSet : gameSets) {
            int red = gameSet.getRed();
            int blue = gameSet.getBlue();
            int green = gameSet.getGreen();

            maxRed = red > maxRed ? red : maxRed;
            maxBlue = blue > maxBlue ? blue : maxBlue;
            maxGreen = green > maxGreen ? green : maxGreen;
        }

        return new CodeConundrumGameSettings(maxRed, maxBlue, maxGreen);
    }

}