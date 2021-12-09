package edu.iu.c212.places.games.blackjack;

import java.util.ArrayList;


public class BlackjackPlayer extends BlackjackParticipant {
    public BlackjackPlayer() {
        super();
        hit();
        hit();
    }

    public String getCurrentTotalsString() {
        ArrayList<String> totals = new ArrayList<>();
        for (Integer cardValue : this.handTotals) {
            if (!totals.contains(String.valueOf(cardValue)) && cardValue <= 21) {
                totals.add(String.valueOf(cardValue));
            }
        }
        String totalString = "Totals(s): ";

        for (String s : totals) {
            totalString += s + " | ";
        }

        return totalString.substring(0, totalString.length() - 3);
    }

    @Override
    public int getBestTotal() {
        int highestValue = 0;
        for (Integer x : this.handTotals) {
            if (x > highestValue && x <= 21) highestValue = x;
        }
        return highestValue;
    }
}
