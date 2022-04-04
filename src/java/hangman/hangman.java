package hangman;

import java.util.Scanner;

public class hangman {
    public static void main(String[] args) {
        String answer;
        int winCount = 0;
        String play;
        Scanner in = new Scanner(System.in);

        var game = new gameLoop();
        answer = game.getAnswer();
        var win = game.play(answer);


        do {
            if (win) {
                winCount++;
            }

            play = in.next();

            if (play.equalsIgnoreCase("y")) {
                answer = game.getAnswer();
                win = game.play(answer);
            } else if (play.equalsIgnoreCase("n")) {
                System.out.println("You won: " + winCount + " times");
                System.exit(0);
            }
            else
            {
                System.out.println("Please enter (y)es or (n)o");
            }
        } while (!play.equalsIgnoreCase("n"));

    }
}
