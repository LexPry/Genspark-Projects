package guessTheNumber;

import java.util.Scanner;

public class guessTheNumber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Ask for user's name and store it for later use
        System.out.println("Hello! What is your name?");
        String name;

        name = in.next();

        // Take user choices and give feed back on if it is too high or low
        // User must guess within 6 tries
        int attempts = 0;
        int guess = 0;

        // initiate game state value to determine if user will play again
        var play = "";

        var check = new checkGuess();

        // Game Play loop
        do {
            // Generate random number between 1 and 20
            int answer = (int) (Math.random() * 20);

            // while loop catch and try, checks for attempts <= 6 and guess != answer
            System.out.println("Well, " + name + " I am thinking of a number between " +
                    "1 and 20. You have six attempts!\nTake a guess.");

            do {
                try {
                    guess = Integer.parseInt(in.next());
                    System.out.println(check.checker(guess, answer));
                    attempts++;
                } catch (NumberFormatException e) {
                    System.out.println("Caught Exception: Number Format Exception.\nPlease enter a valid integer.");
                }
            } while (attempts <= 6 && guess != answer);

            // after max attempts or correct attempt
            if (guess == answer) {
                System.out.println("Good job, " + name + "! You guessed my number" +
                        " in " + attempts + " guesses!");
            } else
                System.out.println("Sorry, You'll have to try again!");

            // Ask if user would like to play again, if yes reset values
            System.out.println("Would you like to play again? (y or n)");

            // loop until y or n is given
            do {
                play = in.next();

                if (play.equals("y")) {
                    guess = 0;
                    attempts = 0;
                } else if (!play.equalsIgnoreCase("y") && !play.equalsIgnoreCase("n"))
                    System.out.println("Please enter y or n");

            } while (!play.equalsIgnoreCase("y") && !play.equalsIgnoreCase("n"));

        } while (play.equalsIgnoreCase("y"));

        // close scanner and exit
        in.close();
        System.exit(0);
    }
}
