package goblins;

/**
 * Goblins Vs Humans
 *
 * @author Alexander Boswell
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Humans Vs Goblins" +
                "\nMove to a goblin to start combat" +
                "\nPress 'q' at anytime to quit");
        new GameLoop();
    }
}
