package edu.iu.c212.places.games.blackjack;

import java.util.ArrayList;

public abstract class BlackjackParticipant {
    protected static ArrayList<String> cards; // TODO: Do I define this here?
    protected int[] handTotals;

    public void hit() {

    }

    public abstract int getBestTotal();
}
