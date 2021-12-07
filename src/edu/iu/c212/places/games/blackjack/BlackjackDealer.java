package edu.iu.c212.places.games.blackjack;

public class BlackjackDealer extends BlackjackParticipant {
    private Card shownCard;
    private int dealerBest;

    public BlackjackDealer() {
        // TODO: Do I have to re-initialize handTotals here? Confused on what this constructor is supposed to do.
        hit();
        hit();
    }

    @Override
    public void hit() {

    }

    public String getPartialHand() {
        return "Dealer has " + shownCard.getCardName() + " + ???";
    }

    public void play() {

    }

    @Override
    public int getBestTotal() {
        return 0;
    }
}
