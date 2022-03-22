import java.util.Scanner;

/**
 * Project 1 Dragon cave
 *
 * @author Alexander Boswell
 */

public class dragonCave {
    public static void main(String[] args) {
        System.out.println("You are in a land full of dragons." +
                "\nIn front of you, you see two caves.\nIn one cave," +
                " the dragon is friendly and will share his treasure " +
                "with you.\nThe other dragon is greedy and hungry and will" +
                " eat you on sight.\nWhich cave will you go into? (1 or 2)");

        /**
         * Instance of a Scanner object to take user input
         * @param in scanner object
         * @param choice stores user input, only takes 1 or 2 as values
         */

        Scanner in = new Scanner(System.in);
        int choice = 0;

        // Try-catch for Number Format Exception
        do {
            try {
                choice = Integer.parseInt(in.next());

                if (choice != 1 && choice != 2) {
                    System.out.println("Not a valid answer\nTry Again!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Exception Caught: Number Format Exception\n" +
                        "Please Enter only integers!\nTry again!");
            }
        } while (choice != 1 && choice != 2); // do-while loop to ensure the answer will always be 1 or 2

        // different outcomes for one and two
        if (choice == 1) {
            System.out.println("You approach the cave...\nIt is dark and spooky..." +
                    "\nA large dragon jumps out in front of you! He opens his jaws and..." +
                    "\nGobbles you down in one bite!");
        } else {
            System.out.println("You approach the cave...\nIt is dark and spooky..." +
                    "\nA large dragon jumps out in front of you! He opens his jaws and..." +
                    "\nShares his treasure with you!");
        }

        System.exit(0);
    }
}
