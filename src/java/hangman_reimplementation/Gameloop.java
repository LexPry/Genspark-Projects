package hangman_reimplementation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Gameloop {

    // variables -----------------
    private String answer;
    private int winCount;
    private String play;
    private int difficulty = 8;
    private int guesses;
    private int currentAttempts;
    private String currentGuess;
    private String correctGuesses;
    private String incorrectGuesses;
    private boolean won;


    // components --------------
    Random rand = new Random();

    // constructor ------------------
    public Gameloop() {
        this.answer = getAnswer();
        this.play = "y";
        this.guesses = 0;
        this.currentAttempts = 0;
        this.correctGuesses = "";
        this.incorrectGuesses = "";
        this.won = false;

        play(answer, guesses);
    }


    // TODO ask the user what length they want the word to be, max length is 11

    public void play(String answer, int guesses) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("\nH A N G M A N");


            while (currentAttempts < answer.length() + 5
                    && !checkAnswer(answer, correctGuesses)
                    && play.equalsIgnoreCase("y")) {
                if (guesses > 0) {
                    System.out.println("Incorrect Letters: " + incorrectGuesses);
                    System.out.println("Correct Letters: " + correctGuesses);
                }

                System.out.println("Enter a guess: ");
                currentGuess = in.next();

                // make sure the guess is one character
                // TODO figure out how to make sure the user only inputs one character without while loop
                if (currentGuess.length() > 1) {
                    System.out.println("Please enter a single letter");
                    currentGuess = in.next();
                }

                // feedback for each guess
                if (answer.contains(currentGuess.toLowerCase())) {
                    System.out.println("Good guess\n");
                    if (!correctGuesses.contains(currentGuess)) {
                        correctGuesses += currentGuess;
                        correctGuesses = displayCorrectGuesses(answer, correctGuesses);
                        won = checkAnswer(answer, correctGuesses);
                    }
                } else {
                    if (!incorrectGuesses.contains(currentGuess)) {
                        incorrectGuesses += currentGuess;
                        currentAttempts++;
                    }
                    System.out.println("Incorrect...\nTry again!");
                }
                guesses++;

                if (won || currentAttempts >= answer.length() + 5) {
                    if (checkAnswer(answer, correctGuesses)) {
                        System.out.println("You win!\nThe word was : " + answer
                                + "\nWould you like to play another game? (y or n)");
                        winCount++;
                    } else {
                        System.out.println("Sorry, the answer was: " + answer +
                                "\nWould you like to play another game? (y or n)");
                    }
                    play = in.next();

                    if (!play.equalsIgnoreCase("y") && !play.equalsIgnoreCase("n")) {
                        System.out.println("Please enter \"y\" or \"n\"");
                        play = in.next();
                    }

                    // reset everything before the loop
                    if (play.equalsIgnoreCase("y")) {
                        answer = getAnswer();
                        currentGuess = "";
                        correctGuesses = "";
                        incorrectGuesses = "";
                        currentAttempts = 0;
                        won = false;
                    } else if (play.equalsIgnoreCase("n")) {
                        System.out.println("Thanks for playing!");
                        System.exit(0);
                    }


                }
            }
        } catch (Exception e) {
            System.out.println("Exception detected : ");
            e.printStackTrace();
        }
    }

    public String getAnswer() {
        String currentGameAnswer;

        List<String> answerChoices;

        try (var dictionary = new FileReader("src/java/hangman_reimplementation/resources/dictionary.txt")) {
            BufferedReader bufferedReader = new BufferedReader(dictionary);
            answerChoices = bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }

        answerChoices = answerChoices.stream().filter(n -> n.length() < difficulty).collect(Collectors.toCollection(ArrayList::new));

        int index = rand.nextInt(answerChoices.size());
        currentGameAnswer = answerChoices.get(index);

        return currentGameAnswer;
    }

    public boolean checkAnswer(String answer, String currentGuesses) {
        List<Character> answerList = answer.chars().mapToObj(a -> (char) a).collect(Collectors.toList());
        List<Character> guessList = currentGuesses.chars().mapToObj(a -> (char) a).collect(Collectors.toList());

        // Filters out characters from guessList that are not in the answerList
        guessList = guessList.stream()
                .filter(answerList::contains)
//                .peek(System.out::println)
                .collect(Collectors.toList());

        if (answer.equalsIgnoreCase(currentGuesses)) return true;
        Collections.sort(answerList);
        Collections.sort(guessList);

        return new HashSet<>(guessList).containsAll(answerList);
    }

    /* trying to make a method to display the correct guesses in the same order and quantity they appear in answer
         Ex: answer = alaska; correctGuesses = a,l; -> prints correctGuesses: alaa */
    public String displayCorrectGuesses(String answer, String correctGuesses) {
        List<Character> guesses = correctGuesses.chars().mapToObj(n -> (char) n).collect(Collectors.toList());

        StringBuilder correct = new StringBuilder();

        answer.chars().mapToObj(n -> (char) n).forEach(ch -> {
            if (guesses.contains(ch)) {
                correct.append(ch);
            }
        });

        return correct.toString();
    }

    // getters and setters
    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
