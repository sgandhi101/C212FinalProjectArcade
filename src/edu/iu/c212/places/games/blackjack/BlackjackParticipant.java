package edu.iu.c212.places.games.blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class BlackjackParticipant {
    protected static ArrayList<Card> cards = new ArrayList<>(Arrays.asList(Card.values()));
    protected ArrayList<Integer> handTotals;

    public BlackjackParticipant() {
        handTotals = new ArrayList<>();
    }

    public void hit() {
        Collections.shuffle(cards);
        Card temp = cards.get(0);
        cards.remove(temp);

        if (!(temp.getValue() == -1)) {
            for (int i = 0; i < handTotals.size(); i++) {
                handTotals.set(i, handTotals.get(i) + temp.getValue());
            }
        } else {
            ArrayList<Integer> tempArrayList = new ArrayList<>();
            for (Integer i : handTotals) {
                tempArrayList.add(i + 1);
                tempArrayList.add(i + 11);
            }

            handTotals.clear();
            handTotals.addAll(tempArrayList);
        }
    }

    public abstract int getBestTotal();
}
