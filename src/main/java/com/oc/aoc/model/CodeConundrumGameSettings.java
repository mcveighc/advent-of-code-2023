package com.oc.aoc.model;

public class CodeConundrumGameSettings {
    
    private int redCubes;
    private int blueClubes;
    private int greenCubes;

    public CodeConundrumGameSettings(int redCubes, int blueClubes, int greenCubes) {
        this.redCubes = redCubes;
        this.blueClubes = blueClubes;
        this.greenCubes = greenCubes;
    }

    public int getRedCubesTotal() {
        return redCubes;
    }

    public int getBlueCubesTotal() {
        return blueClubes;
    }

    public int getGreenCubesTotal() {
        return greenCubes;
    }
}
