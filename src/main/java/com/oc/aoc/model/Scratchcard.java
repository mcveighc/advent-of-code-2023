package com.oc.aoc.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Scratchcard {
    private int cardNumber;
    private Set<Integer> winningNumbers;
    private Set<Integer> scratchcardNumbers;

    public Scratchcard(int cardNumber, List<Integer> winningNumbers, List<Integer> scratchcardNumbers) {
        this.cardNumber = cardNumber;
        this.winningNumbers = new HashSet<>(winningNumbers);
        this.scratchcardNumbers = new HashSet<>(scratchcardNumbers);
    }

    public int getCardNumber() {
        return this.cardNumber;
    }

    public Set<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public Set<Integer> getNumbers() {
        return scratchcardNumbers;
    }

}
