package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lobby extends Place {
    public Lobby(Arcade arcade) {
        super("Lobby", arcade, 0);
    }

    @Override
    public void onEnter(User user) throws IOException {
        arcade.saveUsersToFile();
        List<String> options = new ArrayList<>();
        for (Place p : arcade.getAllPlaces()) {
            options.add(p.toString());
        }

        String menuTitle = "Welcome to the C212 Arcade " + user.getUsername() + "! You're currently in the lobby. " +
                "Your balance is $" + user.getBalance() + ". Where do you want to go next?";
        String selectedPlace = ConsoleUtils.printMenuToConsole(menuTitle, options, true);

        for (Place p : arcade.getAllPlaces()) {
            if (p.toString().equals(selectedPlace)) {
                selectedPlace = p.getPlaceName();
            }
        }
        arcade.transitionArcadeState(selectedPlace);
    }
}
