package hangman_reimplementation;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Gameloop {
    private String answer;
    private int winCount;
    private String play;
    private int difficulty = 8;
    Random rand = new Random();
    // TODO difficulty system with key value pair to determine what length the answer will be


    public String getAnswer() {
        String temp;

        var answerChoices = new ArrayList<String>();
        var dictionary = new File("src/java/hangman_reimplementation/resources/dictionary.txt");
        try (var reader = new Scanner(dictionary)) {
            while (reader.hasNext())
                answerChoices.add(reader.nextLine());
        } catch (Exception e) {
            System.out.println("Exception occurred: ");
            e.printStackTrace();
        }

        answerChoices = answerChoices.stream().filter(n -> n.length() < difficulty).collect(Collectors.toCollection(ArrayList::new));
        int index = rand.nextInt(answerChoices.size());
        temp = answerChoices.get(index);

        return temp;
    }

    public boolean checkAnswer(String answer, String currentGuesses)
    {
        var ans = new ArrayList<Character>();
        var guess = new ArrayList<Character>();

        if (answer.equalsIgnoreCase(currentGuesses)) return true;

        var answerArray = answer.toCharArray();
        var guessesArray = currentGuesses.toCharArray();

        return guess.containsAll(ans);
    }
}
