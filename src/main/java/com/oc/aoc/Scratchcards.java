package com.oc.aoc;


import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oc.aoc.model.Scratchcard;

public class Scratchcards {

    public static int getPointsForCards(String cardsToParse) {
        String[] cards = cardsToParse.split("\n");
        int total = 0;
        for(String card : cards) {
            Scratchcard scratchcard = initCard(card);
            total = total + getPointsForCard(scratchcard);
        }
        return total;
    }
    
    public static int getPointsForCard(Scratchcard scratchcard) {
        Set<Integer> winningNumbers = scratchcard.getWinningNumbers();

        int result = 0;
        for(Integer number : scratchcard.getNumbers()) {
            boolean isMatch = winningNumbers.contains(number);
            if(isMatch) {
                result = result == 0 ? 1 : result * 2;
            }
        }
        return result;
    }

    public static Scratchcard initCard(String card) {
        String[] parsedCard = card.split(":")[1].trim().split("\\|");

        Set<Integer> winningNumbers =  getNumbers(parsedCard[0]);
        Set<Integer> scratchcardNumbers = getNumbers(parsedCard[1]);

        return new Scratchcard(winningNumbers, scratchcardNumbers);
    }

    private static Set<Integer> getNumbers(String input) {
        Pattern integerPattern = Pattern.compile("-?\\d+");
        Matcher matcher = integerPattern.matcher(input);

        Set<Integer> integerList = new HashSet<>();
        while (matcher.find()) {
            int numberToAdd = Integer.parseInt(matcher.group());
            integerList.add(numberToAdd);
        }

        return integerList;
    }

}
