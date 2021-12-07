package edu.iu.c212;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Inventory;
import edu.iu.c212.places.Lobby;
import edu.iu.c212.places.Place;
import edu.iu.c212.places.Store;
import edu.iu.c212.places.games.GuessTheNumberGame;
import edu.iu.c212.places.games.TriviaGame;
import edu.iu.c212.places.games.blackjack.BlackjackGame;
import edu.iu.c212.places.games.hangman.HangmanGame;
import edu.iu.c212.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Arcade implements IArcade {
    User currentUser;
    List<User> allUsers;
    List<Place> allPlaces;

    public Arcade() throws IOException {
        this.allUsers = getUserSaveDataFromFile();
        this.currentUser = getUserOnArcadeEntry();
        this.allPlaces = new ArrayList<>();

        this.allPlaces.add(new Lobby(this));
        this.allPlaces.add(new Inventory(this));
        this.allPlaces.add(new Store(this));

        this.allPlaces.add(new HangmanGame(this));
        this.allPlaces.add(new GuessTheNumberGame(this));
        this.allPlaces.add(new TriviaGame(this));
        this.allPlaces.add(new BlackjackGame(this));

        this.transitionArcadeState("Lobby");
    }

    @Override
    public List<User> getUserSaveDataFromFile() {
        List<User> userSaveData = null;
        try {
            userSaveData = FileUtils.getUserDataFromFile();
        } catch (Exception ex) {
            System.out.println("There was an error");
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        return userSaveData;
    }

    @Override
    public void saveUsersToFile() throws IOException {
        FileUtils.writeUserDataToFile(allUsers);
    }

    @Override
    public void transitionArcadeState(String newPlaceNameToGoTo) throws IOException {
        for (Place p : this.allPlaces) {
            if (p.getPlaceName().equals(newPlaceNameToGoTo)) {
                if (p.getEntryFee() > this.currentUser.getBalance()) {
                    System.out.println("You don't have enough money to enter!");
                    break;
                } else {
                    this.currentUser.setBalance(this.currentUser.getBalance() - p.getEntryFee());
                    saveUsersToFile();
                    p.onEnter(this.currentUser);
                }
            }
        }
        transitionArcadeState("Lobby");
    }

    @Override
    public User getUserOnArcadeEntry() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your username? ");
        boolean userExists = false;
        User currentUser = null;

        String tempUsername = sc.nextLine();
        for (User u : this.allUsers) {
            if (u.getUsername().equals(tempUsername)) {
                userExists = true;
                currentUser = u;
            }
        }
        if (!userExists) {
            currentUser = new User(tempUsername, 50, new ArrayList<>());
            allUsers.add(currentUser);
            saveUsersToFile();
            System.out.println("Welcome to the C212 Arcade " + currentUser.getUsername() + "!");
            System.out.println("Your starting balance will be $50 and you have no items in your inventory.");
        }
        return currentUser;
    }

    @Override
    public List<Place> getAllPlaces() {
        return this.allPlaces;
    }
}
