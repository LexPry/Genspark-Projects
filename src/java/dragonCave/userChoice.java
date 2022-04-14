package dragonCave;

public class userChoice {
    public int choice(int x) {

        if (x != 1 && x != 2) {
            System.out.println("Not a valid answer\nTry Again!");
        }

        return x;
    }
}