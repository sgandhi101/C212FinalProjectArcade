package edu.iu.c212.places.games.hangman;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.games.Game;
import edu.iu.c212.utils.ConsoleUtils;
import edu.iu.c212.utils.http.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HangmanGame extends Game implements IHangmanGame {
    public HangmanGame(Arcade arcade) {
        super("Hangman", arcade, 5.0);
    }

    @Override
    public String getBlurredWord(List<Character> guesses, String word) {
        String blurredWord = "";
        for (char x : word.toCharArray()) {
            boolean letterExists = false;
            for (char guessCharacters : guesses) {
                if (guessCharacters == x) {
                    letterExists = true;
                    blurredWord += guessCharacters;
                    break;
                }
            }
            if (!letterExists) {
                blurredWord += "*";
            }
        }
        return blurredWord;
    }

    @Override
    public List<Character> getValidLexicon() {
        List<Character> allowedChars = new ArrayList<>();
        for (char lowercaseLetters = 'a'; lowercaseLetters <= 'z'; lowercaseLetters++)
            allowedChars.add(lowercaseLetters);

        return allowedChars;
    }

    @Override
    public void onEnter(User user) throws IOException {
        System.out.println("Hello. You will be guessing a randomly generated word. " +
                "If you guess the word within 6 guesses, you win $15. The entry fee for this game was $5.");
        String randomlyGeneratedHangmanWord = HttpUtils.getRandomHangmanWord();
        int incorrectGuessesCount = 0;
        boolean stopGuessing = false;
        List<Character> incorrectGuessesArrayList = new ArrayList<>();
        List<Character> allGuesses = new ArrayList<>();

        while (incorrectGuessesCount < 6) {
            // To check if requirements for solving Hangman game have been met
            if (getBlurredWord(allGuesses, randomlyGeneratedHangmanWord).equals(randomlyGeneratedHangmanWord)) {
                stopGuessing = true;
                System.out.println("Congrats, you won with " + incorrectGuessesCount + " incorrect guesses! You got" +
                        " $15.");
                user.setBalance(user.getBalance() + 15);
                arcade.saveUsersToFile();
                break;
            }

            System.out.println("You have guessed " + incorrectGuessesCount + " times incorrectly. "
                    + incorrectGuessesArrayList);

            System.out.println("The current word is " + getBlurredWord(allGuesses, randomlyGeneratedHangmanWord) +
                    ". Please enter a lower case letter in the following lexicon to guess: " + getValidLexicon());

            Character tempUserHangmanGuess = ConsoleUtils.readLineFromConsole().charAt(0);

            while (allGuesses.contains(tempUserHangmanGuess)) {
                System.out.println("You entered a character you've already guessed. Try again.");
                System.out.println("The current word is " + getBlurredWord(allGuesses, randomlyGeneratedHangmanWord) +
                        ". Please enter a lower case letter in the following lexicon to guess: " + getValidLexicon());
                tempUserHangmanGuess = ConsoleUtils.readLineFromConsole().charAt(0);
            }

            boolean guessInLexicon = false;
            for (Character c : getValidLexicon()) {
                if (c.equals(tempUserHangmanGuess)) {
                    guessInLexicon = true;
                    break;
                }
            }
            if (!guessInLexicon) {
                System.out.println("You entered a character not in the lexicon. You have been penalized regardless");
                incorrectGuessesCount++;
            } else {
                boolean isInGeneratedWord = false;
                for (Character x : randomlyGeneratedHangmanWord.toCharArray()) {
                    if (x == tempUserHangmanGuess) {
                        isInGeneratedWord = true;
                        break;
                    }
                }
                if (!isInGeneratedWord) {
                    incorrectGuessesArrayList.add(tempUserHangmanGuess);
                    incorrectGuessesCount++;
                }
                allGuesses.add(tempUserHangmanGuess);
            }
        }
        if (!stopGuessing) {
            System.out.println("Sorry, you hit the hangman :( The word was " + randomlyGeneratedHangmanWord.toString()
                    + ". You did not win anything.");
        }
        System.out.println("Returning to Lobby");
    }
}