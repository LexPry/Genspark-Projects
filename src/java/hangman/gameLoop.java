package hangman;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class gameLoop {

    public boolean checkAnswer(String answer, String currentGuesses) {
        var ans = new ArrayList<Character>();
        var guess = new ArrayList<Character>();

        if (answer.equalsIgnoreCase(currentGuesses)) return true;

        for (var x : answer.toCharArray()) {
            ans.add(x);
        }

        for (var x : currentGuesses.toCharArray()) {
            guess.add(x);
        }

        Collections.sort(ans);
        Collections.sort(guess);

        return guess.containsAll(ans);
    }

    public boolean play(String answer) {
        int guesses = 0;
        int currentAttempts = 0;
        String currentGuess;
        String correctGuesses = "";
        String incorrectGuesses = "";
        Scanner in = new Scanner(System.in);

        try {
            do {
                System.out.println("\nH A N G M A N\n");
                showHangman(currentAttempts);

                if (guesses > 0) {
                    System.out.println("Missed Letters: " + incorrectGuesses);
                    System.out.println("Correct Guesses: " + correctGuesses);
                }

                System.out.println("please enter a guess!");

                currentGuess = in.next();

                // loop to make sure guess is one character
                if (currentGuess.length() > 1) {
                    while (currentGuess.length() > 1) {
                        System.out.println("Please enter a single letter");
                        currentGuess = in.next();
                    }
                }

                // feedback for each guess, adding to missed and correct lists
                if (answer.contains(currentGuess.toLowerCase())) {
                    System.out.println("Good Guess!");
                    if (!correctGuesses.contains(currentGuess)) {
                        correctGuesses += currentGuess;
                    }
                } else {
                    currentAttempts++;
                    if (!incorrectGuesses.contains(currentGuess)) {
                        incorrectGuesses += currentGuess;
                    }
                    System.out.println("Incorrect...\nTry again!");
                }
                guesses++;

            } while (currentAttempts < answer.length() && !checkAnswer(answer, correctGuesses));
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (checkAnswer(answer, correctGuesses)) {
            System.out.println("You win!\nWould you like to play another game? (y on n)");
            return true;
        } else {
            System.out.println("Sorry, the answer was: " + answer + "\nWould you like to play again? (y or n)");
            return false;
        }
    }

    public void showHangman(int guessCount) {
        switch (guessCount) {
            case 0:
                System.out.println("+----+");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("======");
                break;
            case 1:
                System.out.println("+---+");
                System.out.println("  0 |");
                System.out.println("    |");
                System.out.println("    |");
                System.out.println("======");
                break;
            case 2:
                System.out.println("+----+");
                System.out.println("  0  |");
                System.out.println(" /|\\ |");
                System.out.println("     |");
                System.out.println("======");
                break;
            case 3:
                System.out.println("+----+");
                System.out.println("  0  |");
                System.out.println(" /|\\|");
                System.out.println(" /\\ |");
                System.out.println("======");
                break;
            default:
                System.out.println();
        }
    }

    public String getAnswer() {
        String answer = "";

        try {
            // read file and get answer based on random line
            var answerChoices = new ArrayList<String>();
            var random = new Random();

            var dictionary = new File("src/java/hangman/resources/dictionary.txt");
            Scanner reader = new Scanner(dictionary);
            while (reader.hasNext()) {
                answerChoices.add(reader.nextLine());
            }
            reader.close();
            int index = random.nextInt(answerChoices.size());
            answer = answerChoices.get(index);
        } catch (Exception e)
        {
            System.out.println("An error has occurred: ");
            e.printStackTrace();
        }

        return answer;
    }
}