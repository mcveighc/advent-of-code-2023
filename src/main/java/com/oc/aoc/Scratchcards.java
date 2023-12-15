package com.oc.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oc.aoc.model.Scratchcard;

public class Scratchcards {

    public static int getTotalCardsWithCopies(String cardsToParse) {
        List<Scratchcard> cards = new ArrayList<>(Arrays.asList(cardsToParse.split("\n"))
            .stream()
            .map(c -> initCard(c))
            .toList());
        
        List<Scratchcard> allCards = getCardsWithCopies(cards);
        return allCards.size();
    }

    private static List<Scratchcard> getCardsWithCopies(List<Scratchcard> cards) {
        for (int i = 0; i < cards.size(); i++) {
            Scratchcard card = cards.get(i);
            Set<Integer> winningNums = card.getWinningNumbers();
            int matches = (int) card.getNumbers().stream()
                .filter(n -> winningNums.contains(n))
                .count();

            for (int j = 0; j < matches; j++) {
                int copyIndex = (card.getCardNumber() - 1) + (j + 1);
                Scratchcard copy = cards.get(copyIndex);
                cards.add(copy);
            }
        }

        return cards;
    }

    public static int getPointsForCards(String cardsToParse) {
        String[] cards = cardsToParse.split("\n");
        int total = 0;
        for (String card : cards) {
            Scratchcard scratchcard = initCard(card);
            total = total + getPointsForCard(scratchcard);
        }
        return total;
    }

    public static int getPointsForCard(Scratchcard scratchcard) {
        Set<Integer> winningNumbers = scratchcard.getWinningNumbers();

        int result = 0;
        for (Integer number : scratchcard.getNumbers()) {
            boolean isMatch = winningNumbers.contains(number);
            if (isMatch) {
                result = result == 0 ? 1 : result * 2;
            }
        }
        return result;
    }

    public static Scratchcard initCard(String card) {
        String[] parsedCard = card.split(":")[1].trim().split("\\|");
        String cardNumberStr = card.split(":")[0];
        int cardNumber = getNumbers(cardNumberStr).get(0);

        List<Integer> winningNumbers = getNumbers(parsedCard[0]);
        List<Integer> scratchcardNumbers = getNumbers(parsedCard[1]);

        return new Scratchcard(cardNumber, winningNumbers, scratchcardNumbers);
    }

    private static List<Integer> getNumbers(String input) {
        Pattern integerPattern = Pattern.compile("-?\\d+");
        Matcher matcher = integerPattern.matcher(input);

        List<Integer> integerList = new ArrayList<>();
        while (matcher.find()) {
            int numberToAdd = Integer.parseInt(matcher.group());
            integerList.add(numberToAdd);
        }

        return integerList;
    }

}
