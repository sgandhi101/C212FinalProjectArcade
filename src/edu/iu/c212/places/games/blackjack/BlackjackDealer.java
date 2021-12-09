package edu.iu.c212.places.games.blackjack;

import java.util.Collections;

public class BlackjackDealer extends BlackjackParticipant {
    private Integer shownCard;
    private int dealerBest;

    public BlackjackDealer() {
        super();
        this.hit();
        this.hit();
    }

    @Override
    public void hit() {
        super.hit();
        if (shownCard == null) {
            shownCard = this.handTotals.get(0);
        }
    }

    @Override
    public int getBestTotal() {
        return dealerBest;
    }


    public String getPartialHand() {
        return "Dealer has " + shownCard + " + ???";
    }

    public void play() {
        this.dealerBest = -1;
        boolean continueHitting = true;
        boolean allValuesAreAbove21;

        while (continueHitting) {
            allValuesAreAbove21 = true;
            this.dealerBest = -1;

            this.handTotals.sort(Collections.reverseOrder());
            for (Integer x : this.handTotals) {
                if (x >= 17 && x <= 21) {
                    this.dealerBest = x;
                    continueHitting = false;
                    break;
                }
                if (x <= 21) {
                    allValuesAreAbove21 = false;
                }
            }
            if (!allValuesAreAbove21) hit();
            else break;
        }
    }
}