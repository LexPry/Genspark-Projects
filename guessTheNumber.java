import java.util.Scanner;

public class guessTheNumber {
    public static void main(String[] args) {
        // Generate random number between 1 and 20
        int answer = (int) (Math.random() * 20);

        Scanner in = new Scanner(System.in);
        // Ask for user's name and store it for later use
        System.out.println("Hello! What is your name?");
        String name = in.next();

        System.out.println("Well, " + name + " I am thinking of a number between " +
                "1 and 20. You have six attempts!\nTake a guess.");

        // Take user choices and give feed back on if it is too high or low
        // User must guess within 6 tries
        int attempts = 0;
        int guess = Integer.parseInt(in.next());

        // initiate game state value to determine if user will play again
        var play = "";

        // Game Play loop
        do {
            while (attempts <= 6 && guess != answer) {
                if (guess > answer) {
                    System.out.println("Your guess is too high.\nTake a guess.");
                } else {
                    System.out.println("Your guess is too low.\nTake a guess.");
                }

                guess = Integer.parseInt(in.next());
                attempts++;
            }

            // after max attempts or correct attempt
            if (guess == answer) {
                System.out.println("Good job, " + name + "! You guessed my number" +
                        " in " + attempts + " guesses!");
            } else
                System.out.println("Sorry, You'll have to try again!");

            // Ask if user would like to play again, if yes reset values
            System.out.println("Would you like to play again? (y or n)");
            play = in.next();

            if (play.equals("y")) {
                guess = 0;
                attempts = 0;
            }

        } while (play.equals("y"));

        System.exit(0);
    }
}
