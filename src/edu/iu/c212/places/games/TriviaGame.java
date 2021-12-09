package edu.iu.c212.places.games;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;
import edu.iu.c212.utils.http.HttpUtils;
import edu.iu.c212.utils.http.TriviaQuestion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TriviaGame extends Game {

    public TriviaGame(Arcade arcade) {
        super("Trivia", arcade, 0);
    }

    @Override
    public void onEnter(User user) throws IOException {
        System.out.println("Welcome to C212 Trivia. You get $2 for every correct answer - there are 5 total " +
                "questions in this trivial round.");

        List<TriviaQuestion> triviaQuestionList = HttpUtils.getTriviaQuestions(5);
        int correctQuestionsAnswered = 0;

        for (int question = 1; question < 6; question++) {
            System.out.println("You're on question " + question + ". Ready?");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                ;
            }
            TriviaQuestion tq = triviaQuestionList.get(question - 1);
            String correctAnswer = tq.getCorrectAnswer();

            ArrayList<String> tqAnswers = new ArrayList<>();
            tqAnswers.add(tq.getCorrectAnswer());
            tqAnswers.addAll(tq.getIncorrectAnswers());

            Collections.shuffle(tqAnswers);

            String tempSelectedAnswer = ConsoleUtils.printMenuToConsole(tq.getQuestion(), tqAnswers,
                    true);
            if (tempSelectedAnswer.equals(correctAnswer)) {
                System.out.println("You got it right! You got $2");
                correctQuestionsAnswered++;
                user.setBalance(user.getBalance() + 2);
                arcade.saveUsersToFile();
            } else {
                System.out.println("You got it wrong :( The correct answer is: " + correctAnswer);
            }
        }
        System.out.println("Good game! You got " + correctQuestionsAnswered + " question(s) right.");

        System.out.println("Going back to lobby.");
    }

    @Override
    public String toString() {
        return "Place Name: " + getPlaceName() + " (Cost: $" + getEntryFee() + ") Game?:" + "yes";
    }
}
