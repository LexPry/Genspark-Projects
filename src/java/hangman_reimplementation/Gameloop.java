package hangman_reimplementation;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Gameloop {

    // variables -----------------
    private String answer;
    private String play;
    private String currentGuess;
    private String correctGuesses;
    private String incorrectGuesses;
    private String name;
    private int winCount;
    private int score;
    private int answerLengthCap = 7;
    private int guesses;
    private int currentAttempts;
    private int triesLeft;

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
        this.name = "";

        play(answer, guesses);
    }


    // TODO ask the user what length they want the word to be, max length is 11

    public void play(String answer, int guesses) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("\nH A N G M A N");
            System.out.println("Current High Score: " + highScore());

            if (name.length() == 0) {
                System.out.println("What is your name?");
                String tempName = in.next();
                setName(tempName);
            }


            while (currentAttempts < answer.length() + 5
                    && !checkAnswer(answer, correctGuesses)
                    && play.equalsIgnoreCase("y")) {
                triesLeft = (answer.length() + 5) - currentAttempts;
                if (guesses > 0) {
                    System.out.println("You have: " + triesLeft + " tries left");
                    System.out.println("Incorrect Letters: " + incorrectGuesses);
                    System.out.println("Correct Letters: " + correctGuesses);
                }

                System.out.println("Enter a guess: ");
                currentGuess = in.next();

                // make sure the guess is one character
                if (currentGuess.length() > 1 || !currentGuess.matches("[a-zA-Z]")) {
                    System.out.println("Please enter a single letter");
                    play(this.answer, this.guesses);
                }

                // feedback for each guess
                if (answer.contains(currentGuess.toLowerCase())) {
                    System.out.println("\nGood guess\n");
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
                    System.out.println("\nIncorrect...\nTry again!");
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
                        play = playCheck(play, in);
                    }

                    // reset everything before looping again, or exit if play is "n"
                    if (play.equalsIgnoreCase("y")) {
                        score += currentAttempts - answer.length();
                        answer = getAnswer();
                        currentGuess = "";
                        correctGuesses = "";
                        incorrectGuesses = "";
                        currentAttempts = 0;
                        won = false;
                        if (score > highScore()) {
                            System.out.println("Congrats " + name + " You now have the High-Score:" + score);
                        }
                    } else if (play.equalsIgnoreCase("n")) {
                        score += currentAttempts - answer.length();
                        if (score >= 0) {
                            writeToFile(name, score);
                        } else
                            writeToFile(name, 0);
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

    // methods ---------------------------------------------------------------------------------------------------------

    /**
     * scan file for scores
     * @return int - the highest integer in the file
     */
    public int highScore() {
        List<String> scoreChart = new ArrayList<>();
        int highScore = 0;

        try (var scores = new BufferedReader(new FileReader("src/java/hangman_reimplementation/resources/Score_Chart.txt"))) {
            scoreChart = scores.lines().map(x -> x.replaceAll("[a-zA-Z:,]+", "").trim())
                    .filter(a -> !a.isEmpty())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error detected: ");
            e.printStackTrace();
        }

        if (!scoreChart.isEmpty()) {
            try {
                highScore = scoreChart.stream()
                        .map(Integer::parseInt)
                        .reduce(Integer::max).get();
            } catch (Exception e) {
                System.out.println("Error caught in highScore() : ");
                e.printStackTrace();
            }
        }

        return highScore;
    }

    /**
     * Picks a random line in the Dictionary file and uses it as the answer for the round
     * @return String - answer for the round
     */
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

        if (!answerChoices.isEmpty()) {
            answerChoices = answerChoices.stream()
                    .filter(n -> n.length() < answerLengthCap)
                    .collect(Collectors.toCollection(ArrayList::new));
        }

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


    /*
    Trying to use recursion to always get a y or n without a while loop...
    currently for every loop if play != y/n call again, then check, but it kills scanner in main game loop...
     */

    public String playCheck(String play, Scanner in) {
        String tempResult = play;
        String result = "";
        if (!tempResult.matches("[y n]+")) {
            System.out.println("Please enter \"y\" or \"n\"");
            try {
                tempResult = in.next();
                if (tempResult.matches("[yn]")) {
                    result = tempResult;
                    setPlay(result);
                }
                playCheck(result, in);
            } catch (Exception e) {
                System.out.println("Error: ");
                e.printStackTrace();
            }
        }

        return getPlay();
    }

    public void writeToFile(String name, int score) {
        try (FileWriter writer = new FileWriter("src/java/hangman_reimplementation/resources/Score_Chart.txt", true)) {
            writer.write("\nName: " + name + ", Score: " + score);
        } catch (IOException e) {
            System.out.println("Error Detected: ");
            e.printStackTrace();
        }
    }

    // getters and setters
    public int getAnswerLengthCap() {
        return answerLengthCap;
    }

    public void setAnswerLengthCap(int answerLengthCap) {
        this.answerLengthCap = answerLengthCap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlay(String string) {
        this.play = string;
    }

    public String getPlay() {
        return this.play;
    }
}
