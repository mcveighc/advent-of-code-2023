package com.oc.aoc.model;

public class GearRationEnginePart {
    int number;
    boolean hasAdjacentSymbol;

    public GearRationEnginePart(int partNumber, boolean hasAdjacentSymbol) {
        this.number = partNumber;
        this.hasAdjacentSymbol = hasAdjacentSymbol;
    }

    public int getPartNumber() {
        return number;
    }

    public boolean hasAdjacentSymbol() {
        return hasAdjacentSymbol;
    }
}
