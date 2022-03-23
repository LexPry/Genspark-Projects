package project2;

public class checkGuess {
    public String checker(int guess, int answer) {

        if (guess > answer) {
            return "Your guess is too high.\nTake a guess.";
        } else if (guess < answer) {
            return "Your guess is too low.\nTake a guess.";
        } else
            return "Correct!";

    }
}
