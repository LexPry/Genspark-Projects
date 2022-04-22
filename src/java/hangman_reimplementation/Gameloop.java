package hangman_reimplementation;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Gameloop
    {
    private String answer;
    private String play;
    private String currentGuess;
    private String correctGuesses;
    private String incorrectGuesses;
    private String name;
    private int winCount;
    private int score;
    private int answerLengthCap = 10;
    private int guesses;
    private int currentAttempts;
    private int triesLeft;
    private boolean won;


    // components --------------
    Random rand = new Random();

    // constructor ------------------
    public Gameloop()
        {
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

    /**
     * Test constructor, takes boolean true or false for testing
     *
     * @param l can be true or false, only here for TDD purposes
     */
    Gameloop(boolean l)
        {

        }

    public void play(String answer, int guesses)
        {
        try (Scanner in = new Scanner(System.in))
            {
            if (guesses == 0)
                {
                displayArt();
//            System.out.println("\nH A N G M A N");
                System.out.println("\n\n");
                System.out.println("Current High Score: " + getHighScore());
                }


            if (name.length() == 0)
                {
                System.out.println("What is your name?");
                String tempName = in.nextLine();
                setName(tempName);
                }


            while (currentAttempts < answer.length() + 5
                    && !checkAnswer(answer, getCorrectGuesses())
                    && play.equalsIgnoreCase("y"))
                {
                triesLeft = (answer.length() + 5) - currentAttempts;
                if (guesses > 0)
                    {
                    System.out.println("You have: " + triesLeft + " tries left");
                    System.out.println("Incorrect Letters: " + incorrectGuesses);
                    System.out.println("Correct Letters: " + correctGuesses);
                    }

                System.out.println("Enter a guess: ");
                currentGuess = in.next();

                // make sure the guess is one character
                if (currentGuess.length() > 1 || !currentGuess.matches("[a-zA-Z]"))
                    {
                    System.out.println("Please enter a single letter");
                    play(this.answer, this.guesses);
                    }

                // feedback for each guess
                if (answer.contains(currentGuess.toLowerCase()))
                    {
                    System.out.println("\nGood guess\n");
                    if (!correctGuesses.contains(currentGuess))
                        {
                        correctGuesses += currentGuess;
                        correctGuesses = displayCorrectGuesses(answer, getCorrectGuesses());
                        won = checkAnswer(answer, getCorrectGuesses());
                        }
                    } else
                    {
                    if (!incorrectGuesses.contains(currentGuess))
                        {
                        incorrectGuesses += currentGuess;
                        currentAttempts++;
                        }
                    System.out.println("\nIncorrect...\nTry again!");
                    }
                guesses++;

                if (won || currentAttempts >= answer.length() + 5)
                    {
                    if (checkAnswer(answer, getCorrectGuesses()))
                        {
                        System.out.println("You win!\nThe word was : " + answer + "\n it took you " + guesses
                                + " guesses\nWould you like to play another round? (y or n)");
                        winCount++;
                        } else
                        {
                        System.out.println("Sorry, the answer was: " + answer +
                                "\nWould you like to play another game? (y or n)");
                        }
                    play = in.next();

                    if (!play.equalsIgnoreCase("y") && !play.equalsIgnoreCase("n"))
                        {
                        play = playCheck(play, in);
                        }

                    // reset everything before looping again, or exit if play is "n"
                    if (play.equalsIgnoreCase("y"))
                        {
                        score += currentAttempts - answer.length();
                        answer = getAnswer();
                        setGuesses(0);
                        setCurrentGuess("");
                        setCorrectGuesses("");
                        setIncorrectGuesses("");
                        setCurrentAttempts(0);
                        setWon(false);
                        if (score > getHighScore())
                            {
                            System.out.println("\nCongrats " + getName() + " You now have the High-Score: " + getScore());
                            }
                        } else if (play.equalsIgnoreCase("n"))
                        {
                        score += currentAttempts - answer.length();
                        writeToFile(getName(), Math.max(getScore(), 0));
                        System.out.println("Thanks for playing " + getName() + "!");
                        System.out.println("Your score is: " + getScore());
                        System.exit(0);
                        }


                    }
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NoSuchElementException e)
            {
            System.out.println("Exception detected: ");
            e.printStackTrace();
            }
        }

    // methods ---------------------------------------------------------------------------------------------------------

    /**
     * scan file for scores
     *
     * @return int - the highest integer in the file
     */
    public int getHighScore()
        {
        int highScore = 0;

        List<String> scoreChart = new ArrayList<>();
        try (var scores = new BufferedReader(new FileReader("src/java/hangman_reimplementation/resources/Score_Chart.txt")))
            {
            scoreChart = scores.lines()
                    .map(x -> x.replaceAll("[a-zA-Z:,]+", "").trim())
                    .filter(a -> !a.isEmpty())
                    .collect(Collectors.toList());

            } catch (IOException io)
            {
            System.out.println("Error detected: ");
            io.printStackTrace();
            }

        if (!scoreChart.isEmpty())
            {
            try
                {
                highScore = scoreChart.stream()
                        .map(Integer::parseInt)
                        .reduce(Integer::max).get();

                } catch (NumberFormatException | NoSuchElementException e)
                {
                System.out.println("Error caught: ");
                e.printStackTrace();
                }
            }

        return highScore;
        }

    public void displayArt()
        {
        try (var art = new BufferedReader(new FileReader("src/java/hangman_reimplementation/resources/art.txt")))
            {
            art.lines().forEach(System.out::println);
            } catch (IOException e)
            {
            e.printStackTrace();
            }
        }

    /**
     * Picks a random line in the Dictionary file and uses it as the answer for the round
     *
     * @return String - answer for the round
     */
    public String getAnswer()
        {
        String currentGameAnswer;

        List<String> answerChoices;

        try (var dictionary = new FileReader("src/java/hangman_reimplementation/resources/dictionary.txt"))
            {
            BufferedReader bufferedReader = new BufferedReader(dictionary);
            answerChoices = bufferedReader.lines().collect(Collectors.toList());
            } catch (IOException io)
            {
            io.printStackTrace();
            throw new RuntimeException("Error in getAnswer: " + io.getCause());
            }

        if (!answerChoices.isEmpty())
            {
            answerChoices = answerChoices.stream()
                    .filter(n -> n.length() < answerLengthCap)
                    .collect(Collectors.toCollection(ArrayList::new));
            }

        int index = rand.nextInt(answerChoices.size());
        currentGameAnswer = answerChoices.get(index);

        return currentGameAnswer;
        }

    /**
     * Check if the current guesses contain all of currentAnswer
     *
     * @param answer         this rounds answer
     * @param currentGuesses List of all guesses up until this point
     * @return returns true or false if answer.equals(currentGuesses)
     */
    public boolean checkAnswer(String answer, String currentGuesses)
        {
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
    public String displayCorrectGuesses(String answer, String correctGuesses)
        {
        List<Character> guesses = correctGuesses.chars().mapToObj(n -> (char) n).collect(Collectors.toList());

        StringBuilder correct = new StringBuilder();

        answer.chars().mapToObj(n -> (char) n).forEach(ch -> {
        if (guesses.contains(ch))
            {
            correct.append(ch);
            } else
            correct.append(" _ ");
        });

        return correct.toString();
        }

    /**
     * Capture user input using Scanner passed from main loop
     *
     * @param play "y" or "n"
     * @param in   Scanner
     * @return returns "y" or "n" depending on user input to decide if playing again
     */
    public String playCheck(String play, Scanner in)
        {
        String tempResult = play;
        String result = "";
        if (tempResult.matches("[y n]+"))
            {
            return getPlay();
            }
        System.out.println("Please enter \"y\" or \"n\"");
        try
            {
            tempResult = in.next();
            if (tempResult.matches("[yn]"))
                {
                result = tempResult;
                setPlay(result);
                }
            playCheck(result, in);
            } catch (Exception e)
            {
            System.out.println("Error: ");
            e.printStackTrace();
            }

        return getPlay();
        }

    /**
     * Writes to file, appends to end of file
     *
     * @param name  users name
     * @param score users score
     */
    public void writeToFile(String name, int score)
        {
        try (FileWriter writer = new FileWriter("src/java/hangman_reimplementation/resources/Score_Chart.txt", true))
            {
            writer.write("Name: " + name + ", Score: " + score + "\n");
            } catch (IOException e)
            {
            System.out.println("Error Detected: ");
            e.printStackTrace();
            }
        }

    // getters and setters

    public int getAnswerLengthCap()
        {
        return answerLengthCap;
        }

    public void setAnswerLengthCap(int answerLengthCap)
        {
        this.answerLengthCap = answerLengthCap;
        }

    public void setName(String name)
        {
        this.name = name;
        }

    public void setPlay(String string)
        {
        this.play = string;
        }

    public String getPlay()
        {
        return this.play;
        }

    public void setAnswer(String answer)
        {
        this.answer = answer;
        }

    public String getCurrentGuess()
        {
        return currentGuess;
        }

    public void setCurrentGuess(String currentGuess)
        {
        this.currentGuess = currentGuess;
        }

    public String getCorrectGuesses()
        {
        return correctGuesses;
        }

    public void setCorrectGuesses(String correctGuesses)
        {
        this.correctGuesses = correctGuesses;
        }

    public String getIncorrectGuesses()
        {
        return incorrectGuesses;
        }

    public void setIncorrectGuesses(String incorrectGuesses)
        {
        this.incorrectGuesses = incorrectGuesses;
        }

    public String getName()
        {
        return name;
        }

    public int getWinCount()
        {
        return winCount;
        }

    public void setWinCount(int winCount)
        {
        this.winCount = winCount;
        }

    public int getScore()
        {
        return score;
        }

    public void setScore(int score)
        {
        this.score = score;
        }

    public int getGuesses()
        {
        return guesses;
        }

    public void setGuesses(int guesses)
        {
        this.guesses = guesses;
        }

    public int getCurrentAttempts()
        {
        return currentAttempts;
        }

    public void setCurrentAttempts(int currentAttempts)
        {
        this.currentAttempts = currentAttempts;
        }

    public int getTriesLeft()
        {
        return triesLeft;
        }

    public void setTriesLeft(int triesLeft)
        {
        this.triesLeft = triesLeft;
        }

    public boolean isWon()
        {
        return won;
        }

    public void setWon(boolean won)
        {
        this.won = won;
        }

    }
