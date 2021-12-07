package edu.iu.c212.places.games.blackjack;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.games.Game;

import java.io.IOException;

public class BlackjackGame extends Game {
    public BlackjackGame(Arcade arcade) {
        super("Blackjack", arcade, 20.0);
    }

    @Override
    public void onEnter(User user) throws IOException {

    }
}
