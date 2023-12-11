package com.oc.aoc.model;

import java.util.Set;

public class Scratchcard {
    private Set<Integer> winningNumbers;
    private Set<Integer> scratchcardNumbers;

    public Scratchcard(Set<Integer> winningNumbers, Set<Integer> scratchcardNumbers) {
        this.winningNumbers = winningNumbers;
        this.scratchcardNumbers = scratchcardNumbers;
    }

    public Set<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public Set<Integer> getNumbers() {
        return scratchcardNumbers;
    }

}
