package com.oc.aoc.model;

import java.util.List;

public class CodeConundrumGame {
    private int gameId;
    private List<CodeConundrumGameSet> gameSets;
    private CodeConundrumGameSettings settings;

    public CodeConundrumGame(int gameId, List<CodeConundrumGameSet> gameSets, CodeConundrumGameSettings settings) {
        this.gameId = gameId;
        this.gameSets = gameSets;
        this.settings = settings;
    }

    public Integer getId() {
        return gameId;
    }

    public List<CodeConundrumGameSet> getGameSets() {
        return gameSets;
    }

    public CodeConundrumGameSettings getSettings() {
        return settings;
    }

}
