package edu.iu.c212.places.games;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;

public class GuessTheNumberGame extends Game {

    public GuessTheNumberGame(Arcade arcade) {
        super("Guess the Number", arcade, 5.0);
    }

    @Override
    public void onEnter(User user) throws IOException {
        System.out.println("Welcome to Guess the Number! You'll be guessing a number between 0 and 100.");
        System.out.println("You'll get $10 if you correctly guess the number within 5 tries. Otherwise you get" +
                " nothing.");
        Random rand = new Random();
        int genNum = rand.nextInt(100);
        boolean correctlyGuessed = false;

        for (int guesses = 0; guesses < 5; guesses++) {
            System.out.println("What's your guess?");
            Function<Integer, Boolean> isIntegerFunction = Objects::nonNull;

            int guessedNum = ConsoleUtils.readIntegerLineFromConsoleOrElseComplainAndRetry(
                    isIntegerFunction, "You entered something that is not an integer!");

            if (guessedNum == genNum) {
                correctlyGuessed = true;
                System.out.println("Congrats you correctly guessed the number!");
                System.out.println("You guessed it within 5 tries, so you get $10");
                user.setBalance(user.getBalance() + 10);
                arcade.saveUsersToFile();
                break;
            } else {
                System.out.println("Oh no, you didn't guess correctly.");
                if (guessedNum < genNum) {
                    System.out.println("Guess Higher!");
                } else {
                    System.out.println("Guess Lower!");
                }
            }
        }

        if (!correctlyGuessed) {
            System.out.println("Sorry, you couldn't guess the number so you don't win anything :( The number " +
                    "was " + genNum);
        }
        System.out.println("Returning to Lobby");
    }
}