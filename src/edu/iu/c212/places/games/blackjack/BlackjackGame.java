package edu.iu.c212.places.games.blackjack;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.games.Game;
import edu.iu.c212.utils.ConsoleUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGame extends Game {
    public BlackjackGame(Arcade arcade) {
        super("Blackjack", arcade, 20.0);
    }

    @Override
    public void onEnter(User user) throws IOException {
        BlackjackPlayer bJP = new BlackjackPlayer();
        BlackjackDealer dealer = new BlackjackDealer();
        boolean gameEnd = false;
        List<String> blackjackChoices = new ArrayList<String>();
        blackjackChoices.add("Hit!");
        blackjackChoices.add("Stay");

        while (!gameEnd) {
            System.out.println(dealer.getPartialHand());
            System.out.println(bJP.getCurrentTotalsString());
            String choice = ConsoleUtils.printMenuToConsole("What would you like to do?",
                    blackjackChoices, true);

            if (choice.equals("Hit!")) {
                bJP.hit();
                if (bJP.getBestTotal() == 0) {
                    gameEnd = true;
                }
            } else {
                dealer.play();
                gameEnd = true;
            }
        }

        if (bJP.getBestTotal() == 0) {
            System.out.println("You busted!");
        } else if (dealer.getBestTotal() == -1) {
            System.out.println("You had " + bJP.getBestTotal() + " and the Dealer Busted! You win $50!");
            user.setBalance(user.getBalance() + 50);
            arcade.saveUsersToFile();
        } else {
            if (bJP.getBestTotal() > dealer.getBestTotal()) {
                System.out.println("You beat the dealer! You win $50!");
                user.setBalance(user.getBalance() + 50);
                arcade.saveUsersToFile();
            } else {
                System.out.println("You had " + bJP.getBestTotal() + " and the Dealer had " + dealer.getBestTotal()
                        + ". Sorry, you lost.");
            }
        }
        System.out.println("Going back to lobby.");
    }
}