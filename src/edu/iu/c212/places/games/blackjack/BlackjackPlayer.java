package edu.iu.c212.places.games.blackjack;

import java.util.ArrayList;

// TODO: There are 2 issues in this Class
public class BlackjackPlayer extends BlackjackParticipant {
    public BlackjackPlayer() {
        // TODO: I've initialized HandsTotals in the BlackjackParticipant class is that okay? Or do I have to do it here?
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
        // TODO: Am I supposed to return the lowest value?
        int lowestValue = this.handTotals.get(0);
        for (Integer x : this.handTotals) {
            if (x < lowestValue) lowestValue = x;
        }
        return lowestValue;
    }
}
